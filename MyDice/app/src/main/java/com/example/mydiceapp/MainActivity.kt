package com.example.mydiceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mydiceapp.ui.theme.MyDiceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyDiceAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DiceView()
                }
            }
        }
    }
}

@Composable
fun DiceView() {
    val diceNumber = remember {
        mutableStateOf(1)
    }
    // Dice回転
    var rotationAngle by remember { mutableFloatStateOf(0f) }
    val rotationAnimation by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = tween(durationMillis = 500) // Adjust duration as needed
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person")
                Text(
                    text = "サイコロを振る",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .rotate(rotationAnimation),
                shadowElevation = 8.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = diceNumber.value.toString(),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }
            Row(
                modifier = Modifier.padding(12.dp),
            ) {
                Button(onClick = {
                    diceNumber.value = rollDice(dimension = 4)
                    rotationAngle += 360f
                }) {
                    Text("4面")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    diceNumber.value = rollDice(dimension = 6)
                    rotationAngle += 360f
                }) {
                    Text("6面")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    diceNumber.value = rollDice(dimension = 20)
                    rotationAngle += 360f
                }) {
                    Text("20面")
                }
            }
        }
    }
}

private fun rollDice(dimension: Int): Int {
    return (1..dimension).random()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDiceAppTheme {
        DiceView()
    }
}