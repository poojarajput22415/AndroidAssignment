package com.example.assismentuserlist.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assismentuserlist.repository.UserDataListRepository
import com.example.assismentuserlist.repository.UserDataListRepositoryImpl
import com.example.assismentuserlist.viewmodel.UserListViewModel

class MainViewModelsFactory constructor(private val userDataListRepository: UserDataListRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserListViewModel::class.java!!)) {
            UserListViewModel(this.userDataListRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}