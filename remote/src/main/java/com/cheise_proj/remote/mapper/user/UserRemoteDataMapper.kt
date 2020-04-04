package com.cheise_proj.remote.mapper.user

import com.cheise_proj.data.model.UserData
import com.cheise_proj.remote.mapper.IRemoteMapper
import com.cheise_proj.remote.model.user.UserRemote

/**
 * This class maps to UserRemote in remote or UserData in data
 * class implements IRemoteMapper
 *
 */
class UserRemoteDataMapper :
    IRemoteMapper<UserRemote, UserData> {

    override fun remoteToData(remote: UserRemote): UserData {
        return UserData(
            email = remote.email,
            userId = remote.userId,
            name = remote.name,
            avatarUrl = remote.avatarUrl,
            accessToken = remote.accessToken?:"",
            refreshToken = remote.refreshToken?:""
        )
    }

    override fun dataToRemote(data: UserData): UserRemote {
        return UserRemote(
            email = data.email,
            userId = data.userId,
            name = data.name,
            avatarUrl = data.avatarUrl,
            refreshToken = data.refreshToken,
            accessToken = data.accessToken
        )
    }
}