package com.example.myxmlcounter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var count: Int = 0
    private lateinit var countTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // XMLレイアウトファイルをこのActivityに設定
        setContentView(R.layout.activity_main)

        // XMLで定義したUIコンポーネントをコードから参照
        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)
        resetButton = findViewById(R.id.resetButton)

        incrementButton.setOnClickListener {
            count++
            updateCountDisplay()
        }

        decrementButton.setOnClickListener {
            count--
            updateCountDisplay()
        }

        resetButton.setOnClickListener {
            count = 0
            updateCountDisplay()
        }

        // 初期表示を更新
        updateCountDisplay()
    }

    private fun updateCountDisplay() {
        countTextView.text = count.toString()
    }
}