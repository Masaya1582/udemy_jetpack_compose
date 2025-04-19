package com.example.myjettrivia.repositories

import android.util.Log
import com.example.myjettrivia.data.DataOrException
import com.example.myjettrivia.models.QuestionItem
import com.example.myjettrivia.networks.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionApi) {
	private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()
	suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
		try {
			dataOrException.loading = true
			dataOrException.data = api.getAllQuestions()
			if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
		} catch (exception: Exception) {
			dataOrException.e = exception
			Log.d("Error", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
		}
		return dataOrException
	}
}
