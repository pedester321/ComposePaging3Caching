package com.plcoding.composepaging3caching.login.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    fun postRequest(@Body body: Map<String, String>): Call<ResponseBody>

    companion object{
        const val BASE_URL = "https://userservice-qeo1.onrender.com/"
    }
}