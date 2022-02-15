package com.example.githubapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.api.RetrofitClient
import com.example.githubapp.model.UserModel
import com.example.githubapp.model.UserResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<UserModel>>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponseModel>{
                override fun onResponse(
                    call: Call<UserResponseModel>,
                    responseModel: Response<UserResponseModel>
                ) {
                    if (responseModel.isSuccessful) {
                        listUsers.postValue(responseModel.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponseModel>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getSearchUsers() : LiveData<ArrayList<UserModel>> {
        return listUsers
    }

}