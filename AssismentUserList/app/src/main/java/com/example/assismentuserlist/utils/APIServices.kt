package com.example.assismentuserlist.utils

import com.example.assismentuserlist.modle.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface APIServices {
        @GET("/api/")
        fun getUserList(@Query("results") result:Int): Call<Results?>

        companion object {
            const val BASE_URL = "https://randomuser.me"
        }
}