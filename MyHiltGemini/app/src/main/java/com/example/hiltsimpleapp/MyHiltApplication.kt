package com.example.hiltsimpleapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyHiltApplication: Application() {
	// 通常、ApplicationクラスのonCreate()などで
	// 初期化処理を行いますが、Hiltの基本設定では
	// このクラスに特別なコードを書く必要は今のところありません。
	// @HiltAndroidAppを付けるだけでHiltがよしなにしてくれます。
}
