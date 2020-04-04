package com.cheise_proj.spiice_ui_challenge.di.module.local

import android.app.Application
import androidx.room.Room

import com.cheise_proj.data.source.local.MessageLocalSource
import com.cheise_proj.data.source.local.PostLocalSource
import com.cheise_proj.data.source.local.UserLocalSource
import com.cheise_proj.local.DATABASE_NAME
import com.cheise_proj.local.db.LocalDb
import com.cheise_proj.local.db.dao.MessageDao
import com.cheise_proj.local.db.dao.PostDao
import com.cheise_proj.local.db.dao.UserDao
import com.cheise_proj.local.source.MessageLocalSourceImpl
import com.cheise_proj.local.source.PostLocalSourceImpl
import com.cheise_proj.local.source.UserLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalModule.Binders::class])
class LocalModule {
    @Module
    interface Binders {

        //region USER
        @Binds
        fun bindUserLocalSource(userLocalSourceImpl: UserLocalSourceImpl): UserLocalSource
        //endregion

        //region POST
        @Binds
        fun bindPostLocalSource(postLocalSourceImpl: PostLocalSourceImpl): PostLocalSource
        //endregion

        //region MESSAGE
        @Binds
        fun bindMessageLocalSource(messageLocalSourceImpl: MessageLocalSourceImpl): MessageLocalSource
        //endregion

    }

    @Singleton
    @Provides
    fun provideRoomDb(application: Application): LocalDb {
        return Room
            .databaseBuilder(
                application.applicationContext,
                LocalDb::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(localDatabase: LocalDb): UserDao = localDatabase.userDao()

    @Singleton
    @Provides
    fun provideMessageDao(localDatabase: LocalDb): MessageDao = localDatabase.messageDao()

    @Singleton
    @Provides
    fun providePostDao(localDatabase: LocalDb): PostDao = localDatabase.postDao()


}