package com.murosar.sensors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.murosar.sensors.ui.theme.SensorsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorsTheme {
                val viewModel = viewModel<MainViewModel>()
                
                Sensors(viewModel.isDark.collectAsState().value)
            }
        }
    }
}

@Composable
fun Sensors(isDark: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isDark) Color.DarkGray else Color.White
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isDark) "It's dark Outside" else "It's bright Outside",
            color = if (isDark) Color.White else Color.DarkGray,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SensorsDarkPreview() {
    SensorsTheme {
        Sensors(isDark = true)
    }
}

@Preview(showBackground = true)
@Composable
fun SensorsBrightPreview() {
    SensorsTheme {
        Sensors(isDark = false)
    }
}