package com.murosar.sensors.di

import android.app.Application
import com.murosar.sensors.sensor.LightSensor
import com.murosar.sensors.sensor.MeasurableSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    @Provides
    @Singleton
    fun provideLightSensor(app: Application): MeasurableSensor = LightSensor(app)
}