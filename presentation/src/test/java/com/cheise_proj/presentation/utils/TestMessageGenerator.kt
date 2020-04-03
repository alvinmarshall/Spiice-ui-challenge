package com.cheise_proj.presentation.utils

import com.cheise_proj.presentation.model.Message

object TestMessageGenerator {
    fun getUserMessages(): List<Message> {
        return arrayListOf(
            Message(
                id = "test message uid",
                timestamp = "2020-03-31T15:45:28.627Z",
                content = "test content",
                sender = TestUserGenerator.getUser(),
                receiver = "test receiver email"
            )
        )
    }

}