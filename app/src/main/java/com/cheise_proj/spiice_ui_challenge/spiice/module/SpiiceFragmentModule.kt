package com.cheise_proj.spiice_ui_challenge.spiice.module

import com.cheise_proj.spiice_ui_challenge.spiice.ui.messages.MessagesFragment
import com.cheise_proj.spiice_ui_challenge.spiice.ui.profile.ProfileFragment
import com.cheise_proj.spiice_ui_challenge.spiice.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SpiiceFragmentModule {
    @ContributesAndroidInjector
    fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    fun contributeMessagesFragment(): MessagesFragment
}