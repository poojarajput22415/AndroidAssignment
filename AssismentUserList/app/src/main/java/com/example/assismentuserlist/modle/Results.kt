package com.example.assismentuserlist.modle

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

import java.util.*

data class Results(val results:List<UserDetails>)

@Parcelize
data class UserDetails(val gender:String,val name:Name,val location:Location,val email:String,val registered:Register) :
    Parcelable
@Parcelize
data class Name(val title:String,val first:String,val last:String):Parcelable

@Parcelize
data class Location(val country:String) : Parcelable

@Parcelize
data class Register(val date:String,val age:Int) : Parcelable