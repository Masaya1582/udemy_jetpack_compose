package com.example.jettipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettipapp.components.InputField
import com.example.jettipapp.ui.theme.JetTipAppTheme
import com.example.jettipapp.util.calculateTotalPerPerson
import com.example.jettipapp.util.calculateTotalTip
import com.example.jettipapp.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val splitByState = remember {
                    mutableStateOf(value = 1)
                }
                val range = IntRange(start = 1, endInclusive = 100)
                val tipAmountState = remember {
                    mutableStateOf(value = 0.0)
                }
                val totalPerPersonState = remember {
                    mutableStateOf(value = 0.0)
                }
                BillForm(
                    range = range,
                    splitByState = splitByState,
                    tipAmountState = tipAmountState,
                    totalPerPersonState = totalPerPersonState
                )
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetTipAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}

@Composable
fun TopHeader(totalPerPerson: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(160.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val totalMoney = "%.2f".format(totalPerPerson)
            Text(
                text = stringResource(R.string.total_per_person),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$$totalMoney",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BillForm(
    onValChange: (String) -> Unit = {},
    range: IntRange = 1..100,
    splitByState: MutableState<Int>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>
) {
    val totalBillState = remember {
        mutableStateOf(value = "")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val sliderPositionState = remember {
        mutableStateOf(value = 0f)
    }
    val tipPercentage = (sliderPositionState.value).toInt()

    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(480.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // ヘッダー
            TopHeader(totalPerPerson = totalPerPersonState.value)
            // 入力フィールド
            InputField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            )
            // Split人数フィールド
            SplitNumberField(
                splitByState,
                totalPerPersonState,
                totalBillState,
                tipPercentage,
                range
            )
            // Tip割合表示フィールド
            TipPercentageField(tipAmountState)
            // Tipスライダーフィールド
            TipSliderField(
                tipPercentage,
                sliderPositionState,
                totalBillState,
                tipAmountState,
                totalPerPersonState,
                splitByState
            )
        }
    }
}

@Composable
private fun TipSliderField(
    tipPercentage: Int,
    sliderPositionState: MutableState<Float>,
    totalBillState: MutableState<String>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>,
    splitByState: MutableState<Int>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$tipPercentage %")
        Spacer(modifier = Modifier.height(14.dp))
        Slider(
            value = sliderPositionState.value,
            valueRange = 0f..100f,
            enabled = totalBillState.value.isNotEmpty(),
            onValueChange = { newVal ->
                sliderPositionState.value = newVal
                tipAmountState.value = calculateTotalTip(
                    totalBill = totalBillState.value.toDouble(),
                    tipPercentage = tipPercentage
                )
                totalPerPersonState.value =
                    calculateTotalPerPerson(
                        totalBill = totalBillState.value.toDouble(),
                        splitBy = splitByState.value,
                        tipPercentage = tipPercentage
                    )
            },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        )
    }
}

@Composable
private fun TipPercentageField(tipAmountState: MutableState<Double>) {
    Row(
        modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Tip",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(200.dp))
        Text(
            text = "$ ${tipAmountState.value}",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
}

@Composable
private fun SplitNumberField(
    splitByState: MutableState<Int>,
    totalPerPersonState: MutableState<Double>,
    totalBillState: MutableState<String>,
    tipPercentage: Int,
    range: IntRange
) {
    Row(
        modifier = Modifier.padding(3.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Split",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(120.dp))
        Row(
            modifier = Modifier.padding(horizontal = 3.dp),
            horizontalArrangement = Arrangement.End
        ) {
            RoundIconButton(
                imageVector = Icons.Default.Remove,
                onClick = {
                    splitByState.value =
                        if (splitByState.value > 1) splitByState.value - 1
                        else 1
                    totalPerPersonState.value =
                        calculateTotalPerPerson(
                            totalBill = totalBillState.value.toDouble(),
                            splitBy = splitByState.value,
                            tipPercentage = tipPercentage
                        )
                }
            )
            Text(
                text = "${splitByState.value}",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 9.dp, end = 9.dp)
            )
            RoundIconButton(
                imageVector = Icons.Default.Add,
                onClick = {
                    if (splitByState.value < range.last) {
                        splitByState.value = splitByState.value + 1
                    }
                    totalPerPersonState.value =
                        calculateTotalPerPerson(
                            totalBill = totalBillState.value.toDouble(),
                            splitBy = splitByState.value,
                            tipPercentage = tipPercentage
                        )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetTipAppTheme {
        BillForm()
    }
}
