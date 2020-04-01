package com.cheise_proj.data.mapper.message

import com.cheise_proj.data.mapper.IDataListMapper
import com.cheise_proj.data.mapper.IDataMapper
import com.cheise_proj.data.model.MessageData
import com.cheise_proj.data.model.UserData.Companion.userMapper
import com.cheise_proj.domain.entities.message.MessageEntity

class MessageDataEntityMapper : IDataMapper<MessageData, MessageEntity>,
    IDataListMapper<MessageData, MessageEntity> {
    override fun dataToEntity(data: MessageData): MessageEntity {
        return MessageEntity(
            id = data.id,
            timestamp = data.timestamp,
            content = data.content,
            receiver = data.receiver,
            sender = userMapper().dataToEntity(data.sender)
        )
    }

    override fun entityToData(entity: MessageEntity): MessageData {
        return MessageData(
            id = entity.id,
            timestamp = entity.timestamp,
            content = entity.content,
            receiver = entity.receiver,
            sender = userMapper().entityToData(entity.sender)
        )
    }

    override fun dataListToEntity(dataList: List<MessageData>): List<MessageEntity> {
        val entity = arrayListOf<MessageEntity>()
        dataList.forEach { data -> entity.add(dataToEntity(data)) }
        return entity
    }

    override fun entityListToData(entityList: List<MessageEntity>): List<MessageData> {
        val data = arrayListOf<MessageData>()
        entityList.forEach { entity -> data.add(entityToData(entity)) }
        return data
    }
}