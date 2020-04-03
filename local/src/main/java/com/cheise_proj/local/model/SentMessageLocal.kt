package com.cheise_proj.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cheise_proj.local.mapper.message.SentMessageLocalDataMapper

@Entity(tableName = "sent_messages")
data class SentMessageLocal(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val content: String,
    @Embedded
    val sender: UserLocal,
    val receiver: String,
    val timestamp: String
) {
    companion object {
        fun sentMessageMapper() = SentMessageLocalDataMapper()
    }
}