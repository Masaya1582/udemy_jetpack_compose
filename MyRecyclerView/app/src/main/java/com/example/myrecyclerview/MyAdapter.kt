package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerview.data.MyItem

class MyAdapter(private val itemList: List<MyItem>) :
	RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

	// (1) ViewHolderクラスの定義
	// 各リストアイテムのView要素を保持する。
	class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
		val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView)
	}

	// (2) ViewHolderの作成
	// 新しいViewHolderが必要になったときに呼び出される。
	// ここでリストアイテムのレイアウトをViewオブジェクトに変換（inflate）する。
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item, parent, false)
		return MyViewHolder(view)
	}

	// (3) データのバインド
	// 指定された位置（position）のデータをViewHolderにバインド（表示）する。
	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		val currentItem = itemList[position]
		holder.titleTextView.text = currentItem.title
		holder.subtitleTextView.text = currentItem.subtitle

		// (オプション) アイテムクリックリスナー
		holder.itemView.setOnClickListener {
			Toast.makeText(holder.itemView.context, "Clicked: ${currentItem.title}", Toast.LENGTH_SHORT).show()
		}
	}

	// (4) アイテム数の取得
	override fun getItemCount(): Int {
		return itemList.size
	}
}
