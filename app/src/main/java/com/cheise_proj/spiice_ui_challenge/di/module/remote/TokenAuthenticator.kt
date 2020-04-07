package com.cheise_proj.spiice_ui_challenge.di.module.remote

import android.content.Context
import android.content.SharedPreferences
import com.cheise_proj.remote.api.ApiService
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.AUTHORIZATION_HEADER
import dagger.Lazy
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val context: Context,
    private val sharedPreferences: SharedPreferences,
    private val apiService: Lazy<ApiService>
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshPref = sharedPreferences.getString(
            context.getString(R.string.pref_login_refresh_token_key),
            null
        )
        val accessToken = "Bearer ${getNewToken(refreshPref)}"
        return response.request().newBuilder().header(AUTHORIZATION_HEADER, accessToken).build()
    }

    private fun getNewToken(refreshPref: String?): String {
        if (refreshPref == null) return ""
        val dto = apiService.get().getNewAccessToken(refreshPref).execute().body()
        println("token dto: $dto")
        val newToken = dto?.data?.accessToken
        sharedPreferences
            .edit()
            .putString(context.getString(R.string.pref_login_access_token_key), newToken).commit()
        return newToken?:""
    }

}