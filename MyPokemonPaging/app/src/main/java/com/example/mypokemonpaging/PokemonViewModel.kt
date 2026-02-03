package com.example.mypokemonpaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    // 0から1000の間でランダムな開始位置を決める
    private val randomOffset = Random.nextInt(0, 1000)

    val pokemonList: Flow<PagingData<PokemonDto>> = repository
        .getPokemonList(randomOffset)
        .cachedIn(viewModelScope) // 読み込んだデータをキャッシュして無駄なリロードを防ぐ
}