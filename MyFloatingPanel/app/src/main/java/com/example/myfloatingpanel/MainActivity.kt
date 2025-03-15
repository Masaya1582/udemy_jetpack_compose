package com.example.myfloatingpanel

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfloatingpanel.ui.theme.MyFloatingPanelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFloatingPanelTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FloatingPanelApp()
                }
            }
        }
    }
}

@Composable
fun FloatingPanelApp() {
    var isPanelVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Main Content Area (Think of this as the main room of your app)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Let it take up the remaining space
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Toggle the Panel Below")
                Switch(
                    checked = isPanelVisible,
                    onCheckedChange = { isPanelVisible = it }
                )
            }
        }

        // Floating Panel (Our sliding drawer)
        FloatingPanel(isVisible = isPanelVisible)
    }
}

@Composable
private fun FloatingPanel(isVisible: Boolean) {
    val density = LocalDensity.current

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically {
            with(density) { 200.dp.roundToPx() }
        },
        exit = slideOutVertically {
            with(density) { 200.dp.roundToPx() }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Text("Floating Panel Content", color = White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFloatingPanelTheme {
        FloatingPanelApp()
    }
}