package com.nechaev.mycard.data.model.user

data class UserList(val userList : List<User>) {
    companion object{
        const val serialized_name : String = "users"
    }
}