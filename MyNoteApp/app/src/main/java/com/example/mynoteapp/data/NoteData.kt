package com.example.mynoteapp.data

import com.example.mynoteapp.model.Note

class NoteDataSource {
	companion object {
		fun loadNotes(): List<Note> {
			return listOf(
				Note(title = "近所のカフェ", description = "新しいカフェのコーヒーが美味しかった。次はケーキも試したい。"),
				Note(title = "週末の予定", description = "土曜日は友達と映画、日曜日は公園でピクニック。"),
				Note(title = "読書リスト", description = "村上春樹の新刊、東野圭吾のミステリー、湊かなえの小説。"),
				Note(title = "今日の出来事", description = "電車で面白い人に遭遇。帰り道に綺麗な夕焼けを見た。"),
				Note(title = "欲しいもの", description = "新しいリュックサック、ワイヤレスイヤホン、デザインが良いマグカップ。"),
				Note(title = "旅行の計画", description = "来月は京都へ旅行。おすすめの観光スポットを調べよう。"),
				Note(title = "仕事のメモ", description = "会議の資料作成、プレゼンの練習、顧客へのメール送信。"),
				Note(title = "健康管理", description = "毎日30分のウォーキング、バランスの取れた食事、十分な睡眠。"),
				Note(title = "趣味の時間", description = "ギターの練習、新しい料理に挑戦、絵を描く。"),
				Note(title = "アイデアメモ", description = "アプリの新機能、ブログの記事テーマ、週末に作りたい料理のレシピ。")
			)
		}
	}
}
