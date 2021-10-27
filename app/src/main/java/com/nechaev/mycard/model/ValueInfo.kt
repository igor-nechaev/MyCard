package com.nechaev.mycard.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


data class ValueInfo(val id : String, val charCode : String,
                     val nominal : Int = 1, val name : String,
                     val value: Double, val previousValue : Double
) {
    companion object{
        const val id_deserialized_name = "ID"
        const val chaeCode_deserialized_name = "CharCode"
        const val nominal_deserialized_name = "Nominal"
        const val name_deserialized_name = "Name"
        const val value_deserialized_name = "Value"
        const val previousValue_deserialized_name = "Previous"
        const val null_value_string = "404"
        const val null_value_int = 1
        const val null_value_double : Double = 404.0
    }
}

class ValueInfoDeserializer : JsonDeserializer<ValueInfo>{
    
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ValueInfo {
        val jsonObject = json?.asJsonObject
        val id : String = jsonObject?.get(ValueInfo.id_deserialized_name)?.asString ?: ValueInfo.null_value_string
        val charCode : String = jsonObject?.get(ValueInfo.chaeCode_deserialized_name)?.asString ?: ValueInfo.null_value_string
        val nominal : Int = jsonObject?.get(ValueInfo.nominal_deserialized_name)?.asInt ?: ValueInfo.null_value_int
        val name : String = jsonObject?.get(ValueInfo.name_deserialized_name)?.asString ?: ValueInfo.null_value_string
        val value : Double= jsonObject?.get(ValueInfo.value_deserialized_name)?.asDouble ?: ValueInfo.null_value_double
        val previousValue : Double= jsonObject?.get(ValueInfo.previousValue_deserialized_name)?.asDouble ?: ValueInfo.null_value_double

        return ValueInfo(id = id, charCode = charCode, name = name, nominal = nominal, value = value, previousValue = previousValue)
    }
    
}