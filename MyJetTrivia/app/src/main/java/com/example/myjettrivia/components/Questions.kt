package com.example.myjettrivia.components

import android.graphics.Paint.Align
import android.util.Log
import android.view.animation.AlphaAnimation
import androidx.compose.animation.core.withInfiniteAnimationFrameNanos
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjettrivia.models.QuestionItem
import com.example.myjettrivia.screens.QuestionsViewModel
import com.example.myjettrivia.utils.AppColors
import com.google.android.gms.common.api.internal.BackgroundDetector

@Composable
fun Questions(viewModel: QuestionsViewModel) {
	val questions = viewModel.data.value.data?.toMutableList()
	val questionIndex = remember {
		mutableStateOf(0)
	}
	if (viewModel.data.value.loading == true) {
		CircularProgressIndicator()
		Log.d("Loading", "Questions Loading...")
	} else {
		val question = try {
			questions?.get(questionIndex.value)
		} catch (ex: Exception) {
			null
		}
		if (questions != null) {
			QuestionsDisplay(
				question = question!!,
				questionIndex = questionIndex,
				viewModel = viewModel
			) {
				questionIndex.value = questionIndex.value + 1
			}
		}
	}
}

@Composable
fun QuestionsDisplay(
	question: QuestionItem,
	questionIndex: MutableState<Int>,
	viewModel: QuestionsViewModel,
	onNextClicked: (Int) -> Unit = {}
) {
	val choicesState = remember(question) {
		question.choices.toMutableList()
	}
	val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
	val answerState = remember(question) {
		mutableStateOf<Int?>(null)
	}
	val correctAnswerState = remember(question) {
		mutableStateOf<Boolean?>(null)
	}
	val updateAnswer: (Int) -> Unit = remember(question) {
		{
			answerState.value = it
			correctAnswerState.value = (choicesState[it] == question.answer)
		}
	}
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxHeight(),
		color = AppColors.mDarkPurple
	) {
		Column(
			modifier = Modifier.padding(12.dp),
			verticalArrangement = Arrangement.Top,
			horizontalAlignment = Alignment.Start
		) {
			QuestionTracker(
				counter = questionIndex.value,
				outOf = listOf(question).size
			)
			DrawDottedLine(pathEffect = pathEffect)
			Column(

			) {
				Text(
					text = question.question,
					modifier = Modifier
						.padding(6.dp)
						.align(alignment = Alignment.Start)
						.fillMaxHeight(0.3f),
					fontSize = 18.sp,
					color = AppColors.mOffWhite,
					fontWeight = FontWeight.Bold,
					lineHeight = 22.sp
				)
				// Choices
				choicesState.forEachIndexed { index, answerText ->
					Row(
						modifier = Modifier
							.padding(3.dp)
							.fillMaxWidth()
							.height(45.dp)
							.border(
								width = 4.dp,
								brush = Brush.linearGradient(
									colors = listOf(
										AppColors.mOffDarkPurple,
										AppColors.mOffDarkPurple
									)
								),
								shape = RoundedCornerShape(15.dp)
							)
							.clip(
								RoundedCornerShape(
									topStartPercent = 50,
									topEndPercent = 50,
									bottomEndPercent = 50,
									bottomStartPercent = 50
								)
							)
							.background(Color.Transparent),
						verticalAlignment = Alignment.CenterVertically
					) {
						RadioButton(
							selected = answerState.value == index,
							onClick = {
								updateAnswer(index)
							},
							modifier = Modifier
								.padding(start = 16.dp),
							colors = RadioButtonDefaults.colors(
								selectedColor =
									if (correctAnswerState.value == true && index == answerState.value) {
										Color.Green.copy(alpha = 0.2f)
									} else {
										Color.Red.copy(alpha = 0.2f)
									}
							)
						)
						val annotatedString = buildAnnotatedString {
							withStyle(
								style = SpanStyle(
									fontWeight = FontWeight.Light,
									color = if (correctAnswerState.value == true && index == answerState.value) {
										Color.Green
									} else if (correctAnswerState.value == false && index == answerState.value) {
										Color.Red
									} else {  AppColors.mOffWhite },
									fontSize = 17.sp
								)
							) {
								append(answerText)
							}
						}
						Text(text = annotatedString, modifier = Modifier.padding(6.dp))
					}
				}
				Spacer(modifier = Modifier.padding(8.dp))
				Button(
					onClick = {
						onNextClicked(questionIndex.value)
					},
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 24.dp)
						.height(48.dp)
						.align(alignment = Alignment.CenterHorizontally),
					shape = RoundedCornerShape(34.dp),
					colors = ButtonDefaults.buttonColors(
						containerColor = AppColors.mLightBlue
					)
				) {
					Text(
						text = "Next",
						modifier = Modifier.padding(4.dp),
						color = AppColors.mOffWhite,
						fontSize = 16.sp
					)
				}
			}
		}
	}
}

@Composable
fun DrawDottedLine(pathEffect: PathEffect) {
	Canvas(
		modifier = Modifier
			.fillMaxWidth()
			.height(1.dp)
	) {
		drawLine(
			color = AppColors.mLightGray,
			start = Offset(0f, 0f),
			end = Offset(size.width, y = 0f),
			pathEffect = pathEffect
		)
	}
}

@Composable
fun QuestionTracker(
	counter: Int,
	outOf: Int
) {
	Text(
		text = buildAnnotatedString {
			withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
				withStyle(style = SpanStyle(
					color = AppColors.mLightGray,
					fontWeight = FontWeight.Bold,
					fontSize = 28.sp
				)) {
					append("Questions  $counter/")
					withStyle(style = SpanStyle(
						color = AppColors.mLightGray,
						fontWeight = FontWeight.Light,
						fontSize = 14.sp
					)) {
						append("$outOf")
					}
				}
			}
		},
		modifier = Modifier.padding(20.dp)
	)
}
