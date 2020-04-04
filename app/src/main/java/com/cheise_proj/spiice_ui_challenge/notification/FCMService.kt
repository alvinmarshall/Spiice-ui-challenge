package com.cheise_proj.spiice_ui_challenge.notification

import com.cheise_proj.presentation.notification.IAppNotification
import com.cheise_proj.presentation.utils.IPreference
import com.cheise_proj.spiice_ui_challenge.utils.GetNotificationNavigationUtil
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.android.AndroidInjection
import javax.inject.Inject

class FCMService : FirebaseMessagingService() {
    @Inject
    lateinit var notification: IAppNotification

    @Inject
    lateinit var prefs: IPreference

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        println("data ${p0.data}")
        val data = p0.data
        val getDirection = GetNotificationNavigationUtil(data["name"], data["email"])
        val direction =
            getDirection.getNavigation(applicationContext, data["type"], prefs.getUserSession())
        if (direction < 0) return
        notification.initNotification(
            applicationContext,
            data["title"] ?: "",
            data["body"] ?: "",
            direction
        )
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        println("refresh token $p0")
    }

}