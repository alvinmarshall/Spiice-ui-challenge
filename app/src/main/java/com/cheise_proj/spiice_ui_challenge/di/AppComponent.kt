package com.cheise_proj.spiice_ui_challenge.di

import android.app.Application
import com.cheise_proj.spiice_ui_challenge.App
import com.cheise_proj.spiice_ui_challenge.di.module.ActivityModule
import com.cheise_proj.spiice_ui_challenge.di.module.AppModule
import com.cheise_proj.spiice_ui_challenge.di.module.ServicesModule
import com.cheise_proj.spiice_ui_challenge.di.module.workmanager.WorkerSubComponent
import com.cheise_proj.spiice_ui_challenge.job.WorkerFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class, ServicesModule::class])
interface AppComponent : AndroidInjector<App> {

    fun workerFactory(): WorkerFactory
    fun workerSubComponentBuilder(): WorkerSubComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: App?)
}