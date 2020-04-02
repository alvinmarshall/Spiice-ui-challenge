package com.cheise_proj.remote.mapper.message

import com.cheise_proj.data.model.MessageData
import com.cheise_proj.remote.mapper.IRemoteListMapper
import com.cheise_proj.remote.mapper.IRemoteMapper
import com.cheise_proj.remote.model.message.MessageRemote
import com.cheise_proj.remote.model.user.UserRemote.Companion.userMapper

class MessageRemoteDataMapper : IRemoteMapper<MessageRemote, MessageData>,
    IRemoteListMapper<MessageRemote, MessageData> {
    override fun remoteToData(remote: MessageRemote): MessageData {
        return MessageData(
            id = remote.id,
            sender = userMapper().remoteToData(remote.sender),
            receiver = remote.receiver,
            content = remote.content,
            timestamp = remote.timestamp
        )
    }

    override fun dataToRemote(data: MessageData): MessageRemote {
        return MessageRemote(
            id = data.id,
            sender = userMapper().dataToRemote(data.sender),
            receiver = data.receiver,
            content = data.content,
            timestamp = data.timestamp
        )
    }

    override fun remoteListToData(remoteList: List<MessageRemote>): List<MessageData> {
        val data = arrayListOf<MessageData>()
        remoteList.forEach { remote -> data.add(remoteToData(remote)) }
        return data
    }

    override fun dataListToRemote(dataList: List<MessageData>): List<MessageRemote> {
        val remote = arrayListOf<MessageRemote>()
        dataList.forEach { data -> remote.add(dataToRemote(data)) }
        return remote
    }


}



