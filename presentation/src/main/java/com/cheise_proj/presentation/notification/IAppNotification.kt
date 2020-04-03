package com.cheise_proj.presentation.notification

import android.content.Context

interface ITeacherNotification {
    fun initNotification(context: Context,title: String, message: String, destination: Int)
}