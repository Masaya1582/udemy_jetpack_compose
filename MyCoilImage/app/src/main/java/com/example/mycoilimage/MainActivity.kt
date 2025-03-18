package com.example.mycoilimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.mycoilimage.ui.theme.MyCoilImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCoilImageTheme {
                Surface() {
                    MockImageView()
                }
            }
        }
    }
}

@Composable
fun MockImageView() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "https://masasophi.com/wp-content/uploads/2025/02/updatedog.jpg",
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = "Mock Image",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCoilImageTheme {
        MockImageView()
    }
}