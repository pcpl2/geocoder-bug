package ova.pcpl2lab.testapp

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ova.pcpl2lab.testapp.ui.theme.GeocoderbugTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeocoderbugTheme {
                val context = LocalContext.current
                val geocoder = Geocoder(context, java.util.Locale.US)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var value by remember { mutableStateOf("") }
                    var address by remember { mutableStateOf("") }

                    Column {
                        TextField(
                            value = value,
                            onValueChange = { value = it },
                            label = { Text("Enter country") },
                            maxLines = 2,
                            modifier = Modifier.padding(20.dp)
                        )

                        Button(onClick = {
                            val results = geocoder.getFromLocationName(value, 5);
                            if(results == null || results.isEmpty()) {
                                return@Button
                            }
                            val results2 = geocoder.getFromLocation(results.first().latitude, results.first().longitude, 5);
                            Log.d("Test", "${results2?.size}")
                            address = results2?.firstOrNull()?.locality ?: ""
                        }) {
                            Text(text = "Download data from geocoder")
                        }
                        Text(text = "Your adres is: $address")

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GeocoderbugTheme {
        Text("Android")
    }
}