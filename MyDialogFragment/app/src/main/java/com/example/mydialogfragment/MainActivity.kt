package com.example.mydialogfragment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val showDialogButton: Button = findViewById(R.id.showDialogButton)

        showDialogButton.setOnClickListener {
            // Dialog表示
            MyDialogFragment().show(supportFragmentManager, "my_dialog_tag")
        }
    }
}