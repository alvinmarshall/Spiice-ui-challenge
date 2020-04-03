package com.cheise_proj.local.mapper.message

import com.cheise_proj.data.model.MessageData
import com.cheise_proj.local.mapper.ILocalListMapper
import com.cheise_proj.local.mapper.ILocalMapper
import com.cheise_proj.local.model.SentMessageLocal
import com.cheise_proj.local.model.UserLocal.Companion.userMapper

class SentMessageLocalDataMapper : ILocalMapper<SentMessageLocal, MessageData>,
    ILocalListMapper<SentMessageLocal, MessageData> {
    override fun localToData(local: SentMessageLocal): MessageData {
        return MessageData(
            id = local.id,
            sender = userMapper().localToData(local.sender),
            receiver = local.receiver,
            content = local.content,
            timestamp = local.timestamp
        )
    }

    override fun dataToLocal(data: MessageData): SentMessageLocal {
        return SentMessageLocal(
            id = data.id,
            sender = userMapper().dataToLocal(data.sender),
            receiver = data.receiver,
            content = data.content,
            timestamp = data.timestamp
        )
    }

    override fun localListToData(localList: List<SentMessageLocal>): List<MessageData> {
        val data = arrayListOf<MessageData>()
        localList.forEach { local -> data.add(localToData(local)) }
        return data
    }

    override fun dataListListToLocal(dataToLocalList: List<MessageData>): List<SentMessageLocal> {
        val remote = arrayListOf<SentMessageLocal>()
        dataToLocalList.forEach { data -> remote.add(dataToLocal(data)) }
        return remote
    }

}



