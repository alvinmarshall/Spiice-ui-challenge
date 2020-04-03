package com.cheise_proj.local.utils

import com.cheise_proj.local.model.MessageLocal
import com.cheise_proj.local.model.SentMessageLocal

object TestMessageGenerator {
    fun getReceiveMessages(): List<MessageLocal> {
        return arrayListOf(
            MessageLocal(
                id = "test message uid",
                timestamp = "2020-03-31T15:45:28.627Z",
                content = "test content",
                sender = TestUserGenerator.getUser(),
                receiver = "test receiver email"
            )
        )
    }

    fun getSentMessages(): List<SentMessageLocal> {
        return arrayListOf(
            SentMessageLocal(
                id = "test message uid",
                timestamp = "2020-03-31T15:45:28.627Z",
                content = "test content",
                sender = TestUserGenerator.getUser(),
                receiver = "test receiver email"
            )
        )
    }

}