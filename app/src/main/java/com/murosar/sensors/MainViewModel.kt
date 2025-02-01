package com.murosar.sensors

import androidx.lifecycle.ViewModel
import com.murosar.sensors.di.AccelerometerSensorQualifier
import com.murosar.sensors.di.LightSensorQualifier
import com.murosar.sensors.di.ProximitySensorQualifier
import com.murosar.sensors.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @LightSensorQualifier private val lightSensor: MeasurableSensor,
    @ProximitySensorQualifier private val proximitySensor: MeasurableSensor,
    @AccelerometerSensorQualifier private val accelerometerSensor: MeasurableSensor,
) : ViewModel() {

    private val mutableIsDark = MutableStateFlow(false)
    val isDark: StateFlow<Boolean> = mutableIsDark.asStateFlow()

    private val mutableIsNear = MutableStateFlow(false)
    val isNear: StateFlow<Boolean> = mutableIsNear.asStateFlow()

    private val mutableAccelerometer = MutableStateFlow(floatArrayOf())
    val accelerometer: StateFlow<FloatArray> = mutableAccelerometer.asStateFlow()

    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            mutableIsDark.value = lux < 60f
        }

        proximitySensor.startListening()
        proximitySensor.setOnSensorValuesChangedListener { values ->
            val proximityValue = values[0]
            mutableIsNear.value = proximityValue < 5f
        }

        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            mutableAccelerometer.value = values.toFloatArray()
        }
    }

    override fun onCleared() {
        super.onCleared()

        lightSensor.stopListening()
        proximitySensor.stopListening()
        accelerometerSensor.stopListening()
    }
}