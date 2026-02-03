package com.example.mypokemonpaging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi
) : PokemonRepository {

    override fun getPokemonList(initialOffset: Int): Flow<PagingData<PokemonDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,         // 1ページあたりの件数
                prefetchDistance = 5,   // 残り5件で次のページを読み込み
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonPagingSource(api, initialOffset)
            }
        ).flow
    }
}