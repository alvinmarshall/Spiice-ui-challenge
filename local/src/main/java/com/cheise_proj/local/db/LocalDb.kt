package com.cheise_proj.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cheise_proj.local.db.converter.ListConverters
import com.cheise_proj.local.db.converter.PortfolioConverter
import com.cheise_proj.local.db.dao.MessageDao
import com.cheise_proj.local.db.dao.PostDao
import com.cheise_proj.local.db.dao.UserDao
import com.cheise_proj.local.model.*

@Database(
    entities = [UserLocal::class, ProfileLocal::class, ReviewsLocal::class, PostLocal::class, MessageLocal::class, SentMessageLocal::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverters::class, PortfolioConverter::class)
abstract class LocalDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun messageDao(): MessageDao
}
