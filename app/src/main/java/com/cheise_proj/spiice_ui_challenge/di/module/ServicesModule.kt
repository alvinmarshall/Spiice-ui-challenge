package com.cheise_proj.spiice_ui_challenge.di.module

import com.cheise_proj.spiice_ui_challenge.notification.FCMService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ServicesModule {
    @ContributesAndroidInjector
    fun contributeFCMService(): FCMService

}