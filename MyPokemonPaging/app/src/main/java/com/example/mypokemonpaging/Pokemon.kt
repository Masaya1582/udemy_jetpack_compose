package com.example.mypokemonpaging

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String
)

// data/remote/PokemonResponse.kt (既存のファイルに追加)
// PokemonDtoからPokemonへの変換関数
fun PokemonDto.toDomain(): Pokemon {
    val id = url.split("/").dropLast(1).last().toIntOrNull() ?: 0 // ID取得
    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}