package com.cheise_proj.local.mapper.message

import com.cheise_proj.data.model.MessageData
import com.cheise_proj.local.mapper.ILocalListMapper
import com.cheise_proj.local.mapper.ILocalMapper
import com.cheise_proj.local.model.MessageLocal
import com.cheise_proj.local.model.UserLocal.Companion.userMapper

class MessageLocalDataMapper : ILocalMapper<MessageLocal, MessageData>,
    ILocalListMapper<MessageLocal, MessageData> {
    override fun localToData(local: MessageLocal): MessageData {
        return MessageData(
            id = local.id,
            sender = userMapper().localToData(local.sender),
            receiver = local.receiver,
            content = local.content,
            timestamp = local.timestamp
        )
    }

    override fun dataToLocal(data: MessageData): MessageLocal {
        return MessageLocal(
            id = data.id,
            sender = userMapper().dataToLocal(data.sender),
            receiver = data.receiver,
            content = data.content,
            timestamp = data.timestamp
        )
    }

    override fun localListToData(localList: List<MessageLocal>): List<MessageData> {
        val data = arrayListOf<MessageData>()
        localList.forEach { local -> data.add(localToData(local)) }
        return data
    }

    override fun dataListListToLocal(dataToLocalList: List<MessageData>): List<MessageLocal> {
        val remote = arrayListOf<MessageLocal>()
        dataToLocalList.forEach { data -> remote.add(dataToLocal(data)) }
        return remote
    }

}



