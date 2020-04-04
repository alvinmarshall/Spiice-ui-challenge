package com.cheise_proj.spiice_ui_challenge.di.module.remote

import android.content.Context
import android.content.SharedPreferences
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.AUTHORIZATION_HEADER
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenService @Inject constructor(
    private val context: Context,
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val tokenPref =
            sharedPreferences.getString(context.getString(R.string.pref_login_access_token_key), null)
                ?: ""
        var request = chain.request()
        val token = "Bearer $tokenPref"
        request = request.newBuilder().addHeader(AUTHORIZATION_HEADER, token).build()
        return chain.proceed(request)
    }
}