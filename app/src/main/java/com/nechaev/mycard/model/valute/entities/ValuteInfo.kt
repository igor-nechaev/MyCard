package com.nechaev.mycard.model.valute.entities

import com.google.gson.annotations.SerializedName


data class ValuteInfo(

    @SerializedName("ID") val id : String,

    @SerializedName("CharCode") val charCode : String,

    @SerializedName("Nominal") val nominal : Int,

    @SerializedName("Name") val name : String,

    @SerializedName("Value") val value: Double,

    @SerializedName("Previous") val previousValue : Double

){}
