package com.example.mypokemonpaging

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    /**
     * ポケモンのページングデータを取得する
     * @param initialOffset 初回の取得位置（ランダム）
     */
    fun getPokemonList(initialOffset: Int): Flow<PagingData<PokemonDto>>
}