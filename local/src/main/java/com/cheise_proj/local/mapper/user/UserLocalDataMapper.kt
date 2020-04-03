package com.cheise_proj.local.mapper.user

import com.cheise_proj.data.model.UserData
import com.cheise_proj.local.mapper.ILocalMapper
import com.cheise_proj.local.model.UserLocal


/**
 * This class maps to UserLocal in remote or UserData in data
 * class implements ILocalMapper
 *
 */
class UserLocalDataMapper :
    ILocalMapper<UserLocal, UserData> {
    override fun localToData(local: UserLocal): UserData {
        return UserData(
            email = local.email,
            userId = local.userId,
            name = local.name,
            avatarUrl = local.avatarUrl,
            accessToken = local.accessToken,
            refreshToken = local.refreshToken
        )
    }

    override fun dataToLocal(data: UserData): UserLocal {
        return UserLocal(
            email = data.email,
            userId = data.userId,
            name = data.name,
            avatarUrl = data.avatarUrl,
            refreshToken = data.refreshToken,
            accessToken = data.accessToken
        )
    }

}