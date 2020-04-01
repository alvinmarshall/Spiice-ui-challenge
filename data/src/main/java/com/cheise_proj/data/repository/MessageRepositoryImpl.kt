package com.cheise_proj.data.repository

import com.cheise_proj.data.model.MessageData
import com.cheise_proj.data.model.MessageData.Companion.messageMapper
import com.cheise_proj.data.source.local.MessageLocalSource
import com.cheise_proj.data.source.remote.MessageRemoteSource
import com.cheise_proj.domain.entities.message.MessageEntity
import com.cheise_proj.domain.repository.MessageRepository
import io.reactivex.Observable
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messageRemoteSource: MessageRemoteSource,
    private val messageLocalSource: MessageLocalSource
) : MessageRepository {
    override fun getMessages(): Observable<List<MessageEntity>> {
        val local = messageLocalSource.getMessages()
            .map { t: List<MessageData> -> messageMapper().dataListToEntity(t) }
        return messageRemoteSource.fetchMessages().map { t: List<MessageData> ->
            messageLocalSource.saveMessages(t)
            return@map messageMapper().dataListToEntity(t)
        }.mergeWith(local)
    }

    override fun getSentMessages(): Observable<List<MessageEntity>> {
        val local = messageLocalSource.getSentMessages()
            .map { t: List<MessageData> -> messageMapper().dataListToEntity(t) }
        return messageRemoteSource.fetchSentMessages().map { t: List<MessageData> ->
            messageLocalSource.saveSentMessages(t)
            return@map messageMapper().dataListToEntity(t)
        }.mergeWith(local)
    }

    override fun getSentMessages(receiverEmail: String): Observable<List<MessageEntity>> {
        val local = messageLocalSource.getSentMessages(receiverEmail)
            .map { t: List<MessageData> -> messageMapper().dataListToEntity(t) }
        return messageRemoteSource.fetchSentMessages().map { t: List<MessageData> ->
            messageLocalSource.saveSentMessages(t)
            return@map messageMapper().dataListToEntity(t)
        }.mergeWith(local)
    }

    override fun createMessage(
        postId: String,
        content: String,
        receiverEmail: String
    ): Observable<Boolean> {
        return messageRemoteSource.createMessage(postId, content, receiverEmail)
    }
}