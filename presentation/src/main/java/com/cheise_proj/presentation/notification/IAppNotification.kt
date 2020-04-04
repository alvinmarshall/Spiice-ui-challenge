package com.cheise_proj.presentation.notification

import android.content.Context

interface IAppNotification {
    fun initNotification(context: Context,title: String, message: String, destination: Int)
}