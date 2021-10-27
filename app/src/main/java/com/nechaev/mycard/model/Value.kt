package com.nechaev.mycard.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.lang.reflect.Type

data class Value(val dateString : String, var infoValue : MutableMap<String, ValueInfo>) {
    companion object{
        const val dateString_deserialized_name = "Date"
        const val valute_deserialized_name = "Valute"
    }
}

class ValueDeserializer : JsonDeserializer<Value> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Value {
        val jsonObject = json?.asJsonObject

        val dateString = jsonObject?.get(Value.dateString_deserialized_name)?.asString
        val infoValue : MutableMap<String, ValueInfo> = hashMapOf()

        val jsonValueInfoNamesArray = jsonObject?.get(Value.valute_deserialized_name)!!.asJsonObject

        for ((name, value ) in jsonValueInfoNamesArray.entrySet()){
            val valueInfo : ValueInfo = ValueInfoDeserializer().deserialize(value, null, context)
            infoValue[name] = valueInfo
        }


        return Value(dateString = dateString!!, infoValue = infoValue)

    }

}