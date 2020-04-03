package com.cheise_proj.presentation.mapper.message

import com.cheise_proj.domain.entities.message.MessageEntity
import com.cheise_proj.presentation.mapper.IPresentationListMapper
import com.cheise_proj.presentation.mapper.IPresentationMapper
import com.cheise_proj.presentation.model.Message
import com.cheise_proj.presentation.model.User.Companion.userMapper

class MessageEntityMapper : IPresentationMapper<Message, MessageEntity>,
    IPresentationListMapper<Message, MessageEntity> {

    override fun presentationToEntity(presentation: Message): MessageEntity {
        return MessageEntity(
            id = presentation.id,
            timestamp = presentation.timestamp,
            content = presentation.content,
            receiver = presentation.receiver,
            sender = userMapper().presentationToEntity(presentation.sender)
        )
    }

    override fun entityToPresentation(entity: MessageEntity): Message {
        return Message(
            id = entity.id,
            timestamp = entity.timestamp,
            content = entity.content,
            receiver = entity.receiver,
            sender = userMapper().entityToPresentation(entity.sender)
        )
    }

    override fun presentationListToEntity(presentationList: List<Message>): List<MessageEntity> {
        val entity = arrayListOf<MessageEntity>()
        presentationList.forEach { presentation -> entity.add(presentationToEntity(presentation)) }
        return entity
    }

    override fun entityListToPresentation(entityList: List<MessageEntity>): List<Message> {
        val presentation = arrayListOf<Message>()
        entityList.forEach { entity -> presentation.add(entityToPresentation(entity)) }
        return presentation
    }

}