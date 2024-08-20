package com.plcoding.composepaging3caching.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.plcoding.composepaging3caching.login.data.remote.LoginApi
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginApi: LoginApi
) : ViewModel(){

    var state by mutableStateOf(LoginState())
        private set

    fun updateEmail(input: String){
        state = state.copy(email = input)
    }

    fun updatePassword(input: String) {
        state = state.copy(password = input)
    }
    fun updateToken(input: String) {
        state = state.copy(token = input)
    }

    fun login() {
        val body = mapOf("email" to state.email, "password" to state.password)
        loginApi.postRequest(body).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                updateToken(response.body().toString())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                updateToken("Deu Ruim")
            }
        })
    }


}