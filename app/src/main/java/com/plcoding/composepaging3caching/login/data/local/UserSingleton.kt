package com.plcoding.composepaging3caching.login.data.local

import android.util.Base64
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSingleton @Inject constructor() {
    var id: String? = null
    var name: String? = null
    var email: String? = null
    var token: String? = null

    // Função para definir o token e decodificá-lo
    fun setUser(token: String) {
        this.token = token

        // Decodificar o token e extrair as informações
        val decodedJWT = decodeJWT(token)

        // Extrair informações do token decodificado
        this.id = decodedJWT?.optString("id")
        this.name = decodedJWT?.optString("name")
        this.email = decodedJWT?.optString("email")
    }

    // Função para limpar as informações do usuário
    fun clear() {
        id = null
        name = null
        email = null
        token = null
    }

    // Função para decodificar o token JWT
    private fun decodeJWT(token: String): JSONObject? {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) {
                null
            } else {
                val payload = parts[1]
                val decodedBytes = Base64.decode(payload, Base64.URL_SAFE)
                val decodedString = String(decodedBytes, Charsets.UTF_8)
                JSONObject(decodedString)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}