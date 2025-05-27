package com.example.mytableviewxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycleView: RecyclerView = findViewById(R.id.my_recycler_view)

        recycleView.layoutManager = LinearLayoutManager(this)
        val myData = mutableListOf<MyItem>()
        for (i in 1..20) {
            myData.add(MyItem("タイトル $i", "これはアイテム $i の説明です。"))
        }
        recycleView.adapter = MyAdapter(myData)
    }
}