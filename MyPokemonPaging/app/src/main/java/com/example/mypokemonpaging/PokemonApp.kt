package com.example.mypokemonpaging

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Hiltのコード生成を開始するためのApplicationクラス
 * AndroidManifest.xmlの <application android:name=".PokemonApp"> に登録が必要
 */
@HiltAndroidApp
class PokemonApp : Application()