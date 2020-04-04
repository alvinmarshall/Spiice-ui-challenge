package com.cheise_proj.spiice_ui_challenge.utils

import android.content.Context
import com.cheise_proj.presentation.vo.UserSession
import com.cheise_proj.spiice_ui_challenge.R

data class GetNotificationNavigationUtil(val fcmName: String?, val fcmEmail: String?) {
    companion object {
        private const val NO_LOCATION = -1
    }

    fun getNavigation(context: Context, type: String?, userSession: UserSession): Int {
        with(userSession) {
            return when (type) {
                context.getString(R.string.notification_type_message) -> {
                    if (fcmName == name && fcmEmail == email) {
                        R.id.messagesFragment
                    } else {
                        NO_LOCATION
                    }
                }
                context.getString(R.string.notification_type_register) -> {
                    if (fcmName == name && fcmEmail == email) {
                        R.id.feedFragment
                    } else {
                        NO_LOCATION
                    }
                }

                else -> NO_LOCATION
            }

        }
    }
}
