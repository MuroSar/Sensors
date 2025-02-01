package com.murosar.sensors.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LightSensorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProximitySensorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AccelerometerSensorQualifier