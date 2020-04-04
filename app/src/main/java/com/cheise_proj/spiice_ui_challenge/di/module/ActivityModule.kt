package com.cheise_proj.spiice_ui_challenge.di.module

import com.cheise_proj.spiice_ui_challenge.onBoarding.MainActivity
import com.cheise_proj.spiice_ui_challenge.onBoarding.module.OnBoardFragmentModule
import com.cheise_proj.spiice_ui_challenge.spiice.SpiiceNavActivity
import com.cheise_proj.spiice_ui_challenge.spiice.module.SpiiceFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector(modules = [SpiiceFragmentModule::class])
    fun provideSpiiceNavActivity(): SpiiceNavActivity

    @ContributesAndroidInjector(modules = [OnBoardFragmentModule::class])
    fun provideMainActivity(): MainActivity

}