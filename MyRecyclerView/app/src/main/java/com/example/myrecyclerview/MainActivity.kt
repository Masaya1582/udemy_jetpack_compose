package com.example.myrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerview.data.MyItem

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

		val data = mutableListOf<MyItem>()
		for (i in 1..20) {
			data.add(MyItem("タイトル $i", "サブタイトル $i."))
		}

		recyclerView.layoutManager = LinearLayoutManager(this)
		recyclerView.adapter = MyAdapter(data)
	}
}
