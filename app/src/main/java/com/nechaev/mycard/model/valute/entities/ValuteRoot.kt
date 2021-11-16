package com.nechaev.mycard.model.valute.entities


data class ValuteRoot(

    val dateString : String,

    var infoValute : MutableMap<String, ValuteInfo>

    ){

    companion object{
        const val dateString_deserialized_name = "Date"
        const val value_deserialized_name = "Valute"
    }

}