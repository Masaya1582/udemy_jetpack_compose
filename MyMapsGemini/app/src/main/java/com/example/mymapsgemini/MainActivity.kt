package com.example.mymapsgemini

// Google Maps Compose のインポート
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymapsgemini.ui.theme.MyMapsGeminiTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMapsGeminiTheme {
                MyMapsApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMapsApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Google Maps Demo") })
        }
    ) { paddingValues ->
        GoogleMapsScreen(modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalPermissionsApi::class) // Accompanist Permissionsの実験的API使用のため
@Composable
fun GoogleMapsScreen(modifier: Modifier = Modifier) {
    val bathurstStation = LatLng(43.6669, -79.4116)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bathurstStation, 15f)
    }

    // 位置情報パーミッションの状態を管理
    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    // ユーザーがパーミッションを許可しているかどうか
    val hasLocationPermission = locationPermissionsState.allPermissionsGranted

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (hasLocationPermission) {
            // パーミッションが許可されている場合のみマップを表示
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true), // ここを true に戻す
                uiSettings = MapUiSettings(zoomControlsEnabled = true)
            ) {
                // マーカー、ポリラインなど追加可能
            }
        } else {
            // パーミッションが許可されていない場合、説明と要求ボタンを表示
            Text(
                text = "現在地を表示するには位置情報パーミッションが必要です。",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // パーミッションを要求する
                // iOSで CLLocationManager().requestWhenInUseAuthorization() するのと似ている
                locationPermissionsState.launchMultiplePermissionRequest()
            }) {
                Text("パーミッションを許可する")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMapsGeminiTheme {
        GoogleMapsScreen()
    }
}