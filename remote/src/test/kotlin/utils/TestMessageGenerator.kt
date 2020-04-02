package utils

import com.cheise_proj.remote.model.StatusDto
import com.cheise_proj.remote.model.message.MessageDto
import com.cheise_proj.remote.model.message.MessageRemote

object TestMessageGenerator {
    fun getStatusDto(): StatusDto {
        return StatusDto(status = 201)
    }

    fun getMessageDto(): MessageDto {
        return MessageDto(message = getUserMessages(), status = 200)
    }

    private fun getUserMessages(): List<MessageRemote> {
        return arrayListOf(
            MessageRemote(
                id = "test message uid",
                timestamp = "2020-03-31T15:45:28.627Z",
                content = "test content",
                sender = TestUserGenerator.getUserDto().user,
                receiver = "test receiver email"
            )
        )
    }

}