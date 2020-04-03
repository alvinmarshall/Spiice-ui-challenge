package com.cheise_proj.presentation.utils

import com.cheise_proj.presentation.vo.UserSession

interface IPreference {
    fun setUserSession(userSession: UserSession)
    fun getUserSession(): UserSession
    fun setBackgroundChanger(set: Boolean)
    fun getFirstTimeLogin(): Boolean
    fun getBackgroundChanger(): Boolean
    fun setFirstTimeLogin(set: Boolean)
    fun firstTimeAskingPermission(permission: String, isFirstTime: Boolean)
    fun isFirstTimeAskingPermission(permission: String): Boolean
}