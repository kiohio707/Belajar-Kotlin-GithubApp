package com.example.githubapp.api

import com.example.githubapp.model.DetailUserResponseModel
import com.example.githubapp.model.UserModel
import com.example.githubapp.model.UserResponseModel
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponseModel>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponseModel>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<UserModel>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<UserModel>
}