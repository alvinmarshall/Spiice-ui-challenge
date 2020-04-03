package com.cheise_proj.presentation.mapper.user


import com.cheise_proj.domain.entities.user.ReviewsEntity
import com.cheise_proj.presentation.mapper.IPresentationListMapper
import com.cheise_proj.presentation.mapper.IPresentationMapper
import com.cheise_proj.presentation.model.Reviews
import com.cheise_proj.presentation.model.User.Companion.userMapper

class ReviewEntityMapper : IPresentationMapper<Reviews, ReviewsEntity>,
    IPresentationListMapper<Reviews, ReviewsEntity> {
    override fun presentationToEntity(presentation: Reviews): ReviewsEntity {
        return ReviewsEntity(
            content = presentation.content,
            timestamp = presentation.timestamp,
            rating = presentation.rating,
            id = presentation.id,
            sender = userMapper().presentationToEntity(presentation.sender)
        )
    }

    override fun entityToPresentation(entity: ReviewsEntity): Reviews {
        return Reviews(
            content = entity.content,
            timestamp = entity.timestamp,
            rating = entity.rating,
            id = entity.id,
            sender = userMapper().entityToPresentation(entity.sender)
        )
    }

    override fun presentationListToEntity(presentationList: List<Reviews>): List<ReviewsEntity> {
        val entity = arrayListOf<ReviewsEntity>()
        presentationList.forEach { presentation -> entity.add(presentationToEntity(presentation)) }
        return entity
    }

    override fun entityListToPresentation(entityList: List<ReviewsEntity>): List<Reviews> {
        val presentation = arrayListOf<Reviews>()
        entityList.forEach { entity -> presentation.add(entityToPresentation(entity)) }
        return presentation
    }

}