package com.example.githubapp.api

import com.example.githubapp.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>
}