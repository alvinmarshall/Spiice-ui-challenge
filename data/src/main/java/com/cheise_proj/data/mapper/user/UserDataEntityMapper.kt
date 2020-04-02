package com.cheise_proj.data.mapper.user

import com.cheise_proj.data.mapper.IDataMapper
import com.cheise_proj.data.model.UserData
import com.cheise_proj.domain.entities.user.UserEntity

/**
 * This class maps to UserEntity in domain or UserData in data
 * class implements IDataMapper
 *
 */
class UserDataEntityMapper :
    IDataMapper<UserData, UserEntity> {
    override fun dataToEntity(data: UserData): UserEntity {
        return UserEntity(
            email = data.email,
            userId = data.userId,
            name = data.name,
            avatarUrl = data.avatarUrl,
            accessToken = data.accessToken,
            refreshToken = data.refreshToken
        )
    }

    override fun entityToData(entity: UserEntity): UserData {
        return UserData(
            email = entity.email,
            avatarUrl = entity.avatarUrl,
            name = entity.name,
            userId = entity.userId,
            refreshToken = entity.refreshToken,
            accessToken = entity.accessToken
        )
    }
}