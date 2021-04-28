package com.example.data.remote

import retrofit2.Retrofit

class ServiceGenerator(private val retrofit: Retrofit) {
    fun service(): Service = retrofit.create(Service::class.java)
}