package com.nechaev.mycard.data.model.value

import com.google.gson.annotations.SerializedName


data class ValueInfo(@SerializedName("ID") val id : String, @SerializedName("CharCode") val charCode : String,
                     @SerializedName("Nominal") val nominal : Int = 1,  @SerializedName("Name") val name : String,
                     @SerializedName("Value") val value: Double, @SerializedName("Previous") val previousValue : Double
) {}
