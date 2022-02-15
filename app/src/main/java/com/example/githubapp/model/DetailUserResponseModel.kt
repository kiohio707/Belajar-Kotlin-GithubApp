package com.example.githubapp.model

data class DetailUserResponseModel(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val follower_url: String,
    val following_url: String,
    val name: String,
    val following: Int,
    val follower: Int
)
