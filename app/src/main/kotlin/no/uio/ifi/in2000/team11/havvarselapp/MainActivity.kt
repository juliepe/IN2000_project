package no.uio.ifi.in2000.team11.havvarselapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.team11.havvarselapp.ui.theme.HavvarselAppTheme

import no.uio.ifi.in2000.team11.havvarselapp.data.weather_current_waves.GripfilesDataSource
import no.uio.ifi.in2000.team11.havvarselapp.ui.navigation.NavScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HavvarselAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavScreen()
                    //GribfilesDataSource()
                }
            }
        }
    }
}

@Composable
fun GribfilesDataSource() {
    val dataSource = GripfilesDataSource()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            try {
                val weatherList = dataSource.fetchWeatherCurrentWaves2()

                weatherList.groupedWeatherCurrentWaves.forEach { (area, dataList) ->
                    dataList.forEach { data ->
                        Log.e("WEATHER_DATA: ", "\n\nArea: $area, \nContent: ${data.params.content}, \nUpdated: ${data.updated}, \nURI: ${data.uri}")
                    }
                }

            } catch (e: Exception) {
                // Logg eventuelle feil
                Log.e("ERROR WEATHER_DATA", "feilmelding: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}