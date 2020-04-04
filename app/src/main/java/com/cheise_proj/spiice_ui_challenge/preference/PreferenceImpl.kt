package com.cheise_proj.spiice_ui_challenge.preference

import android.content.Context
import android.content.SharedPreferences
import com.cheise_proj.presentation.utils.IPreference
import com.cheise_proj.presentation.vo.UserSession
import com.cheise_proj.spiice_ui_challenge.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceImpl @Inject constructor(
    private val context: Context,
    private val sharedPreferences: SharedPreferences
) : IPreference {
    override fun setUserSession(userSession: UserSession) {
        val preferences = sharedPreferences.edit()
        with(userSession) {
            preferences.putBoolean(context.getString(R.string.pref_first_time_login_key), true)
            preferences.putBoolean(context.getString(R.string.pref_isLogin_key), isLogin)
            preferences.putString(context.getString(R.string.pref_login_avatar_key), avatarUrl)
            preferences.putString(context.getString(R.string.pref_login_email_key), email)
            preferences.putString(context.getString(R.string.pref_login_name_key), name)
            preferences.putString(
                context.getString(R.string.pref_login_access_token_key),
                accessToken
            )
            preferences.putString(
                context.getString(R.string.pref_login_refresh_token_key),
                refreshToken
            )
        }
        preferences.apply()
    }

    override fun getUserSession(): UserSession {
        val isLogin =
            sharedPreferences.getBoolean(
                context.getString(R.string.pref_isLogin_key),
                false
            )
        val email =
            sharedPreferences.getString(
                context.getString(R.string.pref_login_email_key),
                null
            )


        val name =
            sharedPreferences.getString(
                context.getString(R.string.pref_login_name_key),
                null
            )
        val accessToken =
            sharedPreferences.getString(
                context.getString(R.string.pref_login_access_token_key),
                null
            ) ?: ""

        val refreshToken =
            sharedPreferences.getString(
                context.getString(R.string.pref_login_refresh_token_key),
                null
            ) ?: ""
        val avatarUrl =
            sharedPreferences.getString(
                context.getString(R.string.pref_login_avatar_key),
                null
            )

        return UserSession(isLogin, email, avatarUrl, name).apply {
            this.accessToken = accessToken
            this.refreshToken = refreshToken
        }
    }


    override fun getFirstTimeLogin(): Boolean {
        return sharedPreferences.getBoolean(
            context.getString(R.string.pref_first_time_login_key),
            false
        )
    }


    override fun setFirstTimeLogin(set: Boolean) {
        val preferences = sharedPreferences.edit()
        preferences.putBoolean(context.getString(R.string.pref_first_time_login_key), set)
        preferences.apply()
    }


}