package com.nechaev.mycard.data.network.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nechaev.mycard.data.content.cardHolder.CardHolder
import com.nechaev.mycard.data.content.cardHolder.CardHolderList
import java.lang.reflect.Type




class CardHolderListDeserializer : JsonDeserializer<CardHolderList> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CardHolderList {
        val jsonArray = json?.asJsonObject?.get(CardHolderList.serialized_name)?.asJsonArray
        val cardHolders : MutableList<CardHolder> = mutableListOf()

        for (user in jsonArray!!){
            cardHolders.add(Gson().fromJson(user, CardHolder::class.java))
        }

        return CardHolderList(cardHolderList = cardHolders)
    }

}