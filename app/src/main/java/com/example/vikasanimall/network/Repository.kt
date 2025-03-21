package com.example.vikasanimall.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object Repository {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://storage.googleapis.com/animall.appspot.com/android-interview/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}