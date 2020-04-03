package com.cheise_proj.presentation.mapper.user

import com.cheise_proj.domain.entities.user.UserEntity
import com.cheise_proj.presentation.mapper.IPresentationMapper
import com.cheise_proj.presentation.model.User

/**
 * This class maps to UserEntity in domain or User in presentation
 * class implements IPresentationMapper
 *
 */
class UserEntityMapper :
    IPresentationMapper<User, UserEntity> {
    override fun presentationToEntity(presentation: User): UserEntity {
        return UserEntity(
            email = presentation.email,
            userId = presentation.userId,
            name = presentation.name,
            avatarUrl = presentation.avatarUrl,
            accessToken = presentation.accessToken,
            refreshToken = presentation.refreshToken
        )
    }

    override fun entityToPresentation(entity: UserEntity): User {
        return User(
            email = entity.email,
            avatarUrl = entity.avatarUrl,
            name = entity.name,
            userId = entity.userId,
            refreshToken = entity.refreshToken,
            accessToken = entity.accessToken
        )
    }


}