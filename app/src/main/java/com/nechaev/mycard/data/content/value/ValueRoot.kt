package com.nechaev.mycard.data.content.value

data class ValueRoot(val dateString : String, var infoValue : MutableMap<String, ValueInfo>) {
    companion object{
        const val dateString_deserialized_name = "Date"
        const val value_deserialized_name = "Value"
    }
}