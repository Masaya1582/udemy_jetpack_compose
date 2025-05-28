package com.example.mysharemodal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		val shareButton: Button = findViewById(R.id.shareButton)

		shareButton.setOnClickListener {
			shareContents()
		}
	}

	private fun shareContents() {
		val shareText = "Androidアプリからのシェアです！ Jetpack Composeも楽しいですよ！"
		val shareUrl = "https://developer.android.com/jetpack/compose"

		val sendIntent: Intent = Intent().apply {
			action = Intent.ACTION_SEND
			putExtra(Intent.EXTRA_TEXT, "$shareText\n$shareUrl")
			type = "text/plain"
		}

		val shareIntent = Intent.createChooser(sendIntent, null)
		startActivity(shareIntent)
	}
}
