package com.cheise_proj.presentation.utils

import com.cheise_proj.presentation.vo.UserSession

interface IPreference {
    fun setUserSession(userSession: UserSession)
    fun getUserSession(): UserSession
    fun getFirstTimeLogin(): Boolean
    fun setFirstTimeLogin(set: Boolean)
}