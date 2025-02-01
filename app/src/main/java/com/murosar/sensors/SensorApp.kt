package com.murosar.sensors

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SensorApp @Inject constructor() : Application()