package com.murosar.sensors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

                Sensors(
                    isDark = viewModel.isDark.collectAsState().value,
                    isNear = viewModel.isNear.collectAsState().value,
                    accelerometer = viewModel.accelerometer.collectAsState().value,
                )
            }
        }
    }
}

@Composable
fun Sensors(isDark: Boolean, isNear: Boolean, accelerometer: FloatArray) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isDark) Color.DarkGray else Color.White
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LightSensor:",
                color = if (isDark) Color.White else Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (isDark) "It's dark outside" else "It's bright outside",
                color = if (isDark) Color.White else Color.DarkGray,
            )

            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = "ProximitySensor:",
                color = if (isDark) Color.White else Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (isNear) "It's near enough" else "It's far away",
                color = if (isDark) Color.White else Color.DarkGray,
            )

            if (accelerometer.size >= 3) {
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text = "AccelerometerSensor:",
                    color = if (isDark) Color.White else Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "X: ${accelerometer[0]} m/s²",
                    color = if (isDark) Color.White else Color.DarkGray,
                )
                Text(
                    text = "Y: ${accelerometer[1]} m/s²",
                    color = if (isDark) Color.White else Color.DarkGray,
                )
                Text(
                    text = "Z: ${accelerometer[2]} m/s²",
                    color = if (isDark) Color.White else Color.DarkGray,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SensorsDarkPreview() {
    SensorsTheme {
        Sensors(isDark = true, isNear = true, accelerometer = floatArrayOf(0f, 20f, 38f))
    }
}

@Preview(showBackground = true)
@Composable
fun SensorsBrightPreview() {
    SensorsTheme {
        Sensors(isDark = false, isNear = false, accelerometer = floatArrayOf(0f, 20f, 38f))
    }
}