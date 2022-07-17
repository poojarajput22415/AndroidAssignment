package com.example.assismentuserlist.utils

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit




class RetrofitClient {

    private var instance: RetrofitClient? = null

    private fun retrofitClient(): Retrofit {

        return Retrofit.Builder().baseUrl(APIServices.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Synchronized
    fun getInstance(): RetrofitClient? {
        if (instance == null) {
            instance = RetrofitClient()
        }
        return instance
    }

    fun getMyApi(): APIServices? {
        return retrofitClient().create(APIServices::class.java)
    }
}

