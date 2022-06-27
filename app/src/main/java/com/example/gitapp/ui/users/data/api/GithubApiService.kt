package com.example.gitapp.ui.users.data.api

import com.example.gitapp.ui.users.data.api.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    @GET("users")
    fun getUsers(
        @Query("since") sinceId: Int = 0,
        @Query("per_page") pageSize: Int = 30
    ): Call<List<UserDto>>

}