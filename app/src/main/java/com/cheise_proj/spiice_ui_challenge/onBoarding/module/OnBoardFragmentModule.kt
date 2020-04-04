package com.cheise_proj.spiice_ui_challenge.onBoarding.module

import com.cheise_proj.spiice_ui_challenge.onBoarding.ui.signin.SignInFragment
import com.cheise_proj.spiice_ui_challenge.onBoarding.ui.signup.SignUpFragment
import com.cheise_proj.spiice_ui_challenge.onBoarding.ui.starter.StarterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnBoardFragmentModule {

    @ContributesAndroidInjector
    fun contributeSignInFragment():SignInFragment

    @ContributesAndroidInjector
    fun contributeSignUpFragment():SignUpFragment

    @ContributesAndroidInjector
    fun contributeStarterFragment():StarterFragment
}