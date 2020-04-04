package com.cheise_proj.spiice_ui_challenge.di.module.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.presentation.factory.ViewModelFactory
import com.cheise_proj.presentation.notification.IAppNotification
import com.cheise_proj.presentation.utils.IPreference
import com.cheise_proj.presentation.utils.InputValidation
import com.cheise_proj.presentation.viewmodel.MessageViewModel
import com.cheise_proj.presentation.viewmodel.PostViewModel
import com.cheise_proj.presentation.viewmodel.UserViewModel
import com.cheise_proj.spiice_ui_challenge.common.InputValidationImpl
import com.cheise_proj.spiice_ui_challenge.di.key.ViewModelKey
import com.cheise_proj.spiice_ui_challenge.notification.AppNotificationImpl
import com.cheise_proj.spiice_ui_challenge.preference.PreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [PresentationModule.Binders::class])
class PresentationModule {
    @Module
    interface Binders {

        @Binds
        fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

        //region USER

        @Binds
        @ViewModelKey(UserViewModel::class)
        @IntoMap
        fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

        //endregion

        //region POST

        @Binds
        @ViewModelKey(PostViewModel::class)
        @IntoMap
        fun bindPostViewModel(postViewModel: PostViewModel): ViewModel

        //endregion

        //region MESSAGE

        @Binds
        @ViewModelKey(MessageViewModel::class)
        @IntoMap
        fun bindMessageViewModel(messageViewModel: MessageViewModel): ViewModel

        //endregion

        //region NOTIFICATION
        @Binds
        fun bindAppNotificationImpl(appNotificationImpl: AppNotificationImpl): IAppNotification

        //endregion

        @Binds
        fun bindPreferenceImpl(preferenceImpl: PreferenceImpl): IPreference


        @Binds
        fun bindInputValidation(inputValidationImpl: InputValidationImpl): InputValidation

    }

}