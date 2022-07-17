package com.example.assismentuserlist.handler

import com.example.assismentuserlist.modle.Results

interface ResponseHandler {
    fun onSuccess(successResponse:Results?)
    fun onError(errorMessage:String)
}