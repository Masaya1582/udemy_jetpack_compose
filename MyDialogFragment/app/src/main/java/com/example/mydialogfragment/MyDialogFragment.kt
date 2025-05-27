package com.example.mydialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

// DialogFragmentタイプ
class MyDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dismissButton: Button = view.findViewById(R.id.dialogDismissButton)
        val imageView: ImageView = view.findViewById(R.id.imageView)

        imageView.setImageResource(R.drawable.ic_toronto_city)

        // ボタンがクリックされたらダイアログを閉じる
        dismissButton.setOnClickListener {
            dismiss()
        }
    }
}