package com.example.assismentuserlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assismentuserlist.handler.ResponseHandler
import com.example.assismentuserlist.modle.Results
import com.example.assismentuserlist.modle.UserDetails
import com.example.assismentuserlist.repository.UserDataListRepository
import com.example.assismentuserlist.repository.UserDataListRepositoryImpl
import com.example.assismentuserlist.utils.APIServices
import kotlinx.coroutines.*

class UserListViewModel constructor(private val mainRepository: UserDataListRepository) : ViewModel() {


    private val errorMessage = MutableLiveData<String>()

    private val _userDetailsList = MutableLiveData<List<UserDetails>>()
    val userDetailsList:LiveData<List<UserDetails>> = _userDetailsList

    var job: Job? = null

    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getUserListData(object : ResponseHandler{
                override fun onSuccess(successResponse: Results?) {
                    if (successResponse != null) {
                        _userDetailsList.postValue(successResponse.results)
                    }
                }

                override fun onError(errorMessage: String) {

                    Log.d(TAG, "onError: $errorMessage")
                }
            })
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    companion object{


        private const val TAG = "UserListViewModel"
    }


}