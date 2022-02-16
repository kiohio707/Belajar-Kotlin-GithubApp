package com.example.githubapp.api

import com.example.githubapp.BuildConfig
import com.example.githubapp.model.DetailUserResponseModel
import com.example.githubapp.model.UserModel
import com.example.githubapp.model.UserResponseModel
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.GITHUB_API_TOKEN}")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponseModel>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.GITHUB_API_TOKEN}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponseModel>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.GITHUB_API_TOKEN}")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<UserModel>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.GITHUB_API_TOKEN}")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<UserModel>>
}