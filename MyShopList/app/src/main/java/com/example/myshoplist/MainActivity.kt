package com.example.myshoplist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myshoplist.ui.theme.MyShopListTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyShopListTheme {
				Surface(
					modifier = Modifier
						.padding(top = 100.dp)
						.fillMaxWidth()
						.fillMaxHeight()
				) {
					val shopItems = listOf(
						ShopItem("Coffee Shop", R.drawable.coffee_shop), // Replace with your image
						ShopItem("Bakery", R.drawable.bakery), // Replace with your image
						ShopItem("Clothing Store", R.drawable.clothing_store), // Replace with your image
						ShopItem("Electronics", R.drawable.electronics_store),//Replace with your image
						ShopItem("Grocery", R.drawable.grocery_store),//Replace with your image
						ShopItem("Book Store", R.drawable.book_store)//Replace with your image
					)
					ShopListView(shopItems = shopItems)
				}
			}
		}
	}
}

data class ShopItem(val name: String, val imageResId: Int)

@Composable
private fun ShopListView(shopItems: List<ShopItem>) {
	Row(
		modifier = Modifier
			.horizontalScroll(rememberScrollState())
			.padding(16.dp)
	) {

		shopItems.forEach { shopItem ->
			ShopItemView(shopItem = shopItem)
			Spacer(modifier = Modifier.width(16.dp))
		}
	}
}

@Composable
private fun ShopItemView(shopItem: ShopItem) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier.width(150.dp)
	) {
		Image(
			painter = painterResource(id = shopItem.imageResId),
			contentDescription = shopItem.name,
			modifier = Modifier
				.fillMaxWidth()
				.height(100.dp)
				.clip(RoundedCornerShape(8.dp))
		)
		Spacer(modifier = Modifier.height(8.dp))
		Text(
			text = shopItem.name,
			textAlign = TextAlign.Center
		)
	}
}

@Composable
fun ShopListViewPreview() {
	val shopItems = listOf(
		ShopItem("Coffee Shop", R.drawable.coffee_shop), // Replace with your image
		ShopItem("Bakery", R.drawable.bakery), // Replace with your image
		ShopItem("Clothing Store", R.drawable.clothing_store), // Replace with your image
		ShopItem("Electronics", R.drawable.electronics_store),//Replace with your image
		ShopItem("Grocery", R.drawable.grocery_store),//Replace with your image
		ShopItem("Book Store", R.drawable.book_store)//Replace with your image
	)
	ShopListView(shopItems = shopItems)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyShopListTheme {
		ShopListViewPreview()
	}
}
