package com.example.assismentuserlist.utils

import com.example.assismentuserlist.modle.Name

object DataOperation {


    fun getFullName(name: Name):String{

        return "${name.title}  ${name.first} ${name.last}"
    }

}