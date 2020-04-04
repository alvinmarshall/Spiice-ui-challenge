package com.cheise_proj.spiice_ui_challenge.di.module.data

import com.cheise_proj.data.repository.MessageRepositoryImpl
import com.cheise_proj.data.repository.PostRepositoryImpl
import com.cheise_proj.data.repository.UserRepositoryImpl
import com.cheise_proj.domain.repository.MessageRepository
import com.cheise_proj.domain.repository.PostRepository
import com.cheise_proj.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module(includes = [DataModule.Binders::class])
class DataModule {
    @Module
    interface Binders {
        //region USER
        @Binds
        fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
        //endregion

        //region POST
        @Binds
        fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
        //endregion

        //region MESSAGE
        @Binds
        fun bindMessageRepository(messageRepositoryImpl: MessageRepositoryImpl): MessageRepository
        //endregion

    }

}