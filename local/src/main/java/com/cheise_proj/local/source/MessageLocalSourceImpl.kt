package com.cheise_proj.local.source

import com.cheise_proj.data.model.MessageData
import com.cheise_proj.data.source.local.MessageLocalSource
import com.cheise_proj.local.db.dao.MessageDao
import com.cheise_proj.local.model.MessageLocal
import com.cheise_proj.local.model.MessageLocal.Companion.messageMapper
import com.cheise_proj.local.model.SentMessageLocal
import com.cheise_proj.local.model.SentMessageLocal.Companion.sentMessageMapper
import io.reactivex.Observable
import javax.inject.Inject

class MessageLocalSourceImpl @Inject constructor(
    private val messageDao: MessageDao
) : MessageLocalSource {
    override fun saveMessages(messageDataList: List<MessageData>) {
        println("saving receive messages")
        messageDao.deleteAndSaveReceiveMessages(messageMapper().dataListListToLocal(messageDataList))
    }

    override fun saveSentMessages(messageDataList: List<MessageData>) {
        println("saving sent messages")
        messageDao.deleteAndSaveSentMessages(sentMessageMapper().dataListListToLocal(messageDataList))
    }

    override fun getMessages(): Observable<List<MessageData>> {
        println("getting receive messages")
        return messageDao.getReceiveMessages()
            .map { t: List<MessageLocal> -> messageMapper().localListToData(t) }
    }

    override fun getSentMessages(): Observable<List<MessageData>> {
        println("getting sent messages")
        return messageDao.getSentMessages()
            .map { t: List<SentMessageLocal> -> sentMessageMapper().localListToData(t) }
    }

    override fun getSentMessages(identifier: String): Observable<List<MessageData>> {
        println("getting sent messages with identifier")
        return messageDao.getSentMessages(identifier)
            .map { t: List<SentMessageLocal> -> sentMessageMapper().localListToData(t) }
    }
}