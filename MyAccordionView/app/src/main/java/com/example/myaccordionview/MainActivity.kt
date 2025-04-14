package com.example.myaccordionview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myaccordionview.ui.theme.MyAccordionViewTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyAccordionViewTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					AccordionView(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@Composable
private fun AccordionView(
	modifier: Modifier = Modifier
) {
	Column(
		modifier = Modifier.padding(16.dp)
	) {
		AccordionItem(
			title = "Section 1",
			content = "This is the content of section 1."
		)
		AccordionItem(
			title = "Section 2",
			content = "Here's some more detailed information for section 2."
		)
		AccordionItem(
			title = "Section 3",
			content = "And finally, the content for section 3."
		)
		AccordionItem(
			title = "Section 4",
			content = "This section discusses advanced topics."
		)
		AccordionItem(
			title = "Section 5",
			content = "Quick tips and tricks for beginners."
		)
		AccordionItem(
			title = "Section 6",
			content = "Frequently asked questions and answers."
		)
		AccordionItem(
			title = "Section 7",
			content = "Detailed documentation for developers."
		)
		AccordionItem(
			title = "Section 8",
			content = "User feedback and community discussions."
		)
		AccordionItem(
			title = "Section 9",
			content = "Release notes and update information."
		)
		AccordionItem(
			title = "Section 10",
			content = "Troubleshooting common issues."
		)
	}
}

@Composable
private fun AccordionItem(title: String, content: String) {
	var expanded by remember { mutableStateOf(false) }
	
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.animateContentSize(animationSpec = tween(durationMillis = 300))
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.clickable { expanded = !expanded }
				.padding(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = title,
				style = MaterialTheme.typography.bodyMedium
			)
			Spacer(modifier = Modifier.weight(1f))
			Icon(
				imageVector = Icons.Default.ArrowDropDown,
				contentDescription = "Expand/Collapse",
				modifier = Modifier.rotate(if (expanded) 180f else 0f)
			)
		}
		AnimatedVisibility(visible = expanded) {
			Text(
				text = content,
				modifier = Modifier.padding(16.dp)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyAccordionViewTheme {
		AccordionView()
	}
}
