package com.nechaev.mycard.data.model.user

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("card_number") val cardNumber : String,
                @SerializedName("type") val type : String,
                @SerializedName("cardholder_name") val cardHolder: String,
                @SerializedName("valid") val valid : String,
                @SerializedName("balance") val balance : String,
                @SerializedName("transaction_history") val history: List<Transaction>
) {}