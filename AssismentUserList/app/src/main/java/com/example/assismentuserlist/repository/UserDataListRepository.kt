package com.example.assismentuserlist.repository

import com.example.assismentuserlist.handler.ResponseHandler
import com.example.assismentuserlist.modle.Results
import com.example.assismentuserlist.modle.UserDetails
import com.example.assismentuserlist.utils.UserDetailsPagingSource

interface UserDataListRepository {
    suspend fun getUserListData(responseHandler: ResponseHandler)
    fun userDetailsPagingSource() = UserDetailsPagingSource()
}