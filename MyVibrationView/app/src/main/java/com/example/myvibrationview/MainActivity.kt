package com.example.myvibrationview

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myvibrationview.ui.theme.MyVibrationViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyVibrationViewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpleVibrationView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun SimpleVibrationView(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            // ボタンがクリックされたときに振動させる処理をここに書きます

            // 振動サービスを取得します
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // Android 12 (API S) 以降は VibratorManager を使うのが推奨です
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                // Android 11 (API R) 以前はこちら
                @Suppress("DEPRECATION") // 古いAPIですが、下位互換のために必要です
                context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            // 振動エフェクトを作成します
            // VibrationEffect.createOneShot(時間(ms), 強さ) で単発の振動を作れます
            // 強さは -1 (デフォルト) か 1〜255 の範囲です。
            // VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK) など、
            // 事前定義された効果を使うのも一般的です（クリック、ダブルクリックなど）
            val vibrationEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // API 26 (Oreo) 以降で VibrationEffect が使えます
                VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE) // 100msの短い振動
            } else {
                // API 25 (Nougat) 以前はこちら (古い vibrate メソッドを使います)
                @Suppress("DEPRECATION")
                null // API 26未満では VibrationEffect は使えないため null にします
            }

            // 振動を実行します
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // API 26 以降
                vibrator.vibrate(vibrationEffect)
            } else if (vibrationEffect == null) {
                // API 25 以前で VibrationEffect が使えない場合
                @Suppress("DEPRECATION")
                vibrator.vibrate(100) // 100ms振動
            }
            // 注: 実際には権限チェックを runtime で行うのがより堅牢ですが、
            // ここでは simplicity を優先しています。
        }) {
            Text("ボタンを押して振動！")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyVibrationViewTheme {
        SimpleVibrationView()
    }
}