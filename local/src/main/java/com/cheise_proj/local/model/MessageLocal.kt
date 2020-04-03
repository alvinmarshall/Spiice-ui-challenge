package com.cheise_proj.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cheise_proj.local.mapper.message.MessageLocalDataMapper

@Entity(tableName = "receive_messages")
data class MessageLocal(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val content: String,
    @Embedded
    val sender: UserLocal,
    val receiver: String,
    val timestamp: String
) {
    companion object {
        fun messageMapper() = MessageLocalDataMapper()
    }
}