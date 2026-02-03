package com.example.mypokemonpaging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mypokemonpaging.ui.theme.MyPokemonPagingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // プロジェクト作成時に自動生成されたTheme名に合わせてくれ
            MyPokemonPagingTheme {
                PokemonListScreen()
            }
        }
    }
}