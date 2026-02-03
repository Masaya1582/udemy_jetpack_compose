package com.example.mypokemonpaging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.paging.map
import com.example.mypokemonpaging.toDomain
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    // ViewModelからPagingDataのFlowを収集し、LazyPagingItemsに変換
    val pokemonPagingItems: LazyPagingItems<Pokemon> = viewModel.pokemonList
        .map { pagingData ->
            pagingData.map { dto -> dto.toDomain() }
        }
        .collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokémon List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // ポケモンアイテムの表示
                items(
                    count = pokemonPagingItems.itemCount,
                    key = pokemonPagingItems.itemKey { it.id }, // 一意のキーを指定（リコンポジション最適化）
                    contentType = pokemonPagingItems.itemContentType { "PokemonItem" }
                ) { index ->
                    val pokemon = pokemonPagingItems[index]
                    pokemon?.let {
                        PokemonListItem(pokemon = it)
                    }
                }

                // 読み込み状態のフッター
                pokemonPagingItems.loadState.apply {
                    when {
                        refresh is LoadState.Loading -> { // 初回読み込み or スワイプ更新
                            item { LoadingItem() }
                        }
                        append is LoadState.Loading -> { // 追加読み込み中
                            item { LoadingItem() }
                        }
                        refresh is LoadState.Error || append is LoadState.Error -> { // エラー発生時
                            item {
                                Text(
                                    text = "Failed to load Pokemons",
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}