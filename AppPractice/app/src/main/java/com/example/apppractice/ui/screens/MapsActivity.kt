package com.example.apppractice.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.apppractice.ui.screens.ui.theme.AppPracticeTheme
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

class MapsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
               MapsContentX(innerPadding)
           }
        }
    }
}
// 5b3ce3597851110001cf62489812eebca9ff4771a151f715a706888b
@Composable
fun MapsContentX(paddingValues: PaddingValues) {


}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AppPracticeTheme {
        Greeting2("Android")
    }
}