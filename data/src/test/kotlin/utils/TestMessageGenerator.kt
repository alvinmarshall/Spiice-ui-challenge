package utils

import com.cheise_proj.data.model.MessageData

object TestMessageGenerator {
    fun getUserMessages(): List<MessageData> {
        return arrayListOf(
            MessageData(
                id = "test message uid",
                timestamp = "2020-03-31T15:45:28.627Z",
                content = "test content",
                sender = TestUserGenerator.getUser(),
                receiver = "test receiver email"
            )
        )
    }

}