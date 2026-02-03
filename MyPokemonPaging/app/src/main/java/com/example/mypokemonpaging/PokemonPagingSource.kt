package com.example.mypokemonpaging

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PokemonPagingSource(
    private val api: PokeApi,
    private val initialOffset: Int // 初回ランダムoffset用
) : PagingSource<Int, PokemonDto>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDto> {
        return try {
            val offset = params.key ?: initialOffset
            val limit = params.loadSize

            val response = api.getPokemonList(offset = offset, limit = limit)

            LoadResult.Page(
                data = response.results,
                prevKey = if (offset <= 0) null else offset - limit,
                nextKey = if (response.results.isEmpty()) null else offset + limit
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}