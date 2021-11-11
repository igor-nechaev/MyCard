package com.nechaev.mycard.data.network.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nechaev.mycard.data.content.value.ValueInfo
import com.nechaev.mycard.data.content.value.ValueRoot
import java.lang.reflect.Type

class ValueDeserializer : JsonDeserializer<ValueRoot> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ValueRoot {
        val jsonObject = json?.asJsonObject
        val gson = Gson()

        val dateString = jsonObject?.get(ValueRoot.dateString_deserialized_name)?.asString
        val infoValue : MutableMap<String, ValueInfo> = hashMapOf()

        val jsonValueInfoNamesArray = jsonObject?.get(ValueRoot.value_deserialized_name)!!.asJsonObject
        for ((name, value ) in jsonValueInfoNamesArray.entrySet()){
            val valueInfo : ValueInfo = gson.fromJson(value, ValueInfo::class.java)
            infoValue[name] = valueInfo
        }

        return ValueRoot(dateString = dateString!!, infoValue = infoValue)
    }

}