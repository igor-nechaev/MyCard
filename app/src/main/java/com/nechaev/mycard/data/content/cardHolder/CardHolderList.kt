package com.nechaev.mycard.data.content.cardHolder

data class CardHolderList(val cardHolderList : List<CardHolder>) {
    companion object{
        const val serialized_name : String = "users"
    }
}