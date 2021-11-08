package com.nechaev.mycard.data.model.user

import com.google.gson.annotations.SerializedName

data class Transaction(@SerializedName("title") val title : String,
                       @SerializedName("icon_url") val iconUrl : String,
                       @SerializedName("date") val date : String,
                       @SerializedName("amount") val amount : String) {}