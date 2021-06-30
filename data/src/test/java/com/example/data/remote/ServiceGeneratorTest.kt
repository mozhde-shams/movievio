package com.example.data.remote

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit

@RunWith(MockitoJUnitRunner::class)
class ServiceGeneratorTest {

    @Mock
    private lateinit var retrofit: Retrofit

    @Mock
    private lateinit var service: Service

    @Test
    fun service() {
        Mockito.`when`(retrofit.create(Service::class.java)).thenReturn(service)
        val serviceGenerator = ServiceGenerator(retrofit)
        val generatedService = serviceGenerator.service()

        Assert.assertEquals(service, generatedService)
    }

}