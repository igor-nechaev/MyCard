package com.nechaev.mycard.data.model.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nechaev.mycard.data.model.user.User
import com.nechaev.mycard.data.model.user.UserList
import java.lang.reflect.Type




class UserListDeserializer : JsonDeserializer<UserList> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): UserList {
        val jsonArray = json?.asJsonObject?.get(UserList.serialized_name)?.asJsonArray
        val users : MutableList<User> = mutableListOf()

        for (user in jsonArray!!){
            users.add(Gson().fromJson(user, User::class.java))
        }

        return UserList(userList = users)
    }

}