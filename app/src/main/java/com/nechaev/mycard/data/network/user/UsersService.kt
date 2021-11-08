package com.nechaev.mycard.data.network.user

import com.nechaev.mycard.data.model.user.UserList
import retrofit2.http.GET
import retrofit2.Call

interface UsersService {
    @GET("users.json")
    fun getUserList() : Call<UserList>
}