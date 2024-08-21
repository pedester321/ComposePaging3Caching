package com.plcoding.composepaging3caching.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.plcoding.composepaging3caching.login.data.local.UserSingleton
import com.plcoding.composepaging3caching.login.data.remote.LoginApi
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginApi: LoginApi,
    private val userSingleton: UserSingleton
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
                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()?.string() // Aqui você obtém o conteúdo da resposta como string
                    // Tentar interpretar o corpo como JSON com token
                    try {
                        val jsonObject = responseBody?.let { JSONObject(it) }
                        val token = jsonObject?.getString("token")
                        if (token != null) {
                            userSingleton.setUser(token)
                            updateToken(userSingleton.name.toString())
                        }
                    } catch (e: JSONException) {
                        // Caso não seja um JSON válido, tratamos como uma mensagem de erro
                        updateToken("Erro: $responseBody")
                    }
                } else {
                    updateToken("Erro na resposta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                updateToken("Deu Ruim: ${t.message}")
            }
        })
    }


}