package com.murosar.sensors.di

import android.app.Application
import com.murosar.sensors.sensor.AccelerometerSensor
import com.murosar.sensors.sensor.LightSensor
import com.murosar.sensors.sensor.MeasurableSensor
import com.murosar.sensors.sensor.ProximitySensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    @Provides
    @Singleton
    @LightSensorQualifier
    fun provideLightSensor(app: Application): MeasurableSensor = LightSensor(app)

    @Provides
    @Singleton
    @ProximitySensorQualifier
    fun provideProximitySensor(app: Application): MeasurableSensor = ProximitySensor(app)

    @Provides
    @Singleton
    @AccelerometerSensorQualifier
    fun provideAccelerometerSensor(app: Application): MeasurableSensor = AccelerometerSensor(app)
}