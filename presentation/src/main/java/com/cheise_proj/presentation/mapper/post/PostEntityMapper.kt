package com.cheise_proj.presentation.mapper.post

import com.cheise_proj.domain.entities.posts.PostEntity
import com.cheise_proj.presentation.mapper.IPresentationListMapper
import com.cheise_proj.presentation.mapper.IPresentationMapper
import com.cheise_proj.presentation.model.Post
import com.cheise_proj.presentation.model.User.Companion.userMapper

class PostEntityMapper : IPresentationMapper<Post, PostEntity>,
    IPresentationListMapper<Post, PostEntity> {
    override fun presentationToEntity(presentation: Post): PostEntity {
        return PostEntity(
            id = presentation.id,
            timestamp = presentation.timestamp,
            description = presentation.description,
            user = userMapper().presentationToEntity(presentation.user),
            amount = presentation.amount,
            header = presentation.header,
            proposition = presentation.proposition,
            skills = presentation.skills
        )
    }

    override fun entityToPresentation(entity: PostEntity): Post {
        return Post(
            id = entity.id,
            timestamp = entity.timestamp,
            description = entity.description,
            user = userMapper().entityToPresentation(entity.user),
            amount = entity.amount,
            header = entity.header,
            proposition = entity.proposition,
            skills = entity.skills
        )
    }

    override fun presentationListToEntity(presentationList: List<Post>): List<PostEntity> {
        val entity = arrayListOf<PostEntity>()
        presentationList.forEach { presentation -> entity.add(presentationToEntity(presentation)) }
        return entity
    }

    override fun entityListToPresentation(entityList: List<PostEntity>): List<Post> {
        val presentation = arrayListOf<Post>()
        entityList.forEach { entity -> presentation.add(entityToPresentation(entity)) }
        return presentation
    }

}