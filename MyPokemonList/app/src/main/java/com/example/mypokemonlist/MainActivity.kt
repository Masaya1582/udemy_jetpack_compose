package com.example.mypokemonlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.emptyIntList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypokemonlist.ui.theme.MyPokemonListTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPokemonListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokemonList()
                }
            }
        }
    }
}

@Serializable
data class PokemonListResponse(
    val results: List<PokemonResult>
)

@Serializable
data class PokemonResult(
    val name: String,
    val url: String
)

suspend fun fetchPokemonNames(): List<String>? = withContext(Dispatchers.IO) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://pokeapi.co/api/v2/pokemon?limit=20") // Limit to 20 Pokemon for simplicity
        .build()

    try {
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            val pokemonListResponse = Json.decodeFromString(PokemonListResponse.serializer(), responseBody ?: "")
            return@withContext pokemonListResponse.results.map { it.name }
        } else {
            println("API request failed: ${response.code}")
            return@withContext null
        }
    } catch (e: Exception) {
        println("Error fetching Pokemon data: ${e.message}")
        return@withContext null
    }
}

@Composable
fun PokemonList() {
    val pokemonNames = remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(Unit) {
        val result = fetchPokemonNames()
        if (result != null) {
            pokemonNames.value = result
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if (pokemonNames.value.isEmpty()) {
            Text("Loading...")
        } else {
            pokemonNames.value.forEach { name ->
                Text(name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyPokemonListTheme {
        PokemonList()
    }
}