package com.murosar.sensors

import androidx.lifecycle.ViewModel
import com.murosar.sensors.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    lightSensor: MeasurableSensor
) : ViewModel() {

    private val mutableIsDark = MutableStateFlow(false)
    val isDark: StateFlow<Boolean> = mutableIsDark.asStateFlow()

    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            mutableIsDark.value = lux < 60f
        }
    }
}