package com.example.assismentuserlist.repository

import android.util.Log
import com.example.assismentuserlist.handler.ResponseHandler
import com.example.assismentuserlist.modle.Results
import com.example.assismentuserlist.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataListRepositoryImpl: UserDataListRepository {



    override suspend fun getUserListData(responseHandler: ResponseHandler){
        val call: Call<Results?>? = RetrofitClient().getInstance()?.getMyApi()?.getUserList(
            RESULT_COUNT)

        call?.enqueue(object : Callback<Results?> {
            override fun onResponse(call: Call<Results?>, response: Response<Results?>) {

                Log.d(TAG, "onResponse: ${response.body()}")
               responseHandler.onSuccess(response.body())

            }

            override fun onFailure(call: Call<Results?>, t: Throwable) {

                Log.d(TAG, "onFailure: ${t.localizedMessage}")
                responseHandler.onError(t.localizedMessage)
            }
        })

    }

    companion object {

        private const val TAG = "UserDataListRepositoryI"
        private const val RESULT_COUNT = 100
    }
}