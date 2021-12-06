package com.nechaev.mycard.model.valute.entities

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.HashSet

class ValuteDeserializer : JsonDeserializer<ValuteRoot> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ValuteRoot {
        val jsonObject = json?.asJsonObject
        val gson = Gson()

        val dateString = jsonObject?.get(ValuteRoot.dateString_deserialized_name)?.asString
        val infoValute : MutableMap<String, ValuteInfo> = hashMapOf()

        val jsonValueInfoNamesArray = jsonObject?.get(ValuteRoot.value_deserialized_name)!!.asJsonObject

        for ((name, value ) in jsonValueInfoNamesArray.entrySet()){
            val valuteInfo : ValuteInfo = gson.fromJson(value, ValuteInfo::class.java)
            infoValute[name] = valuteInfo
        }

        return ValuteRoot(dateString = dateString!!, infoValute = infoValute)
    }

}