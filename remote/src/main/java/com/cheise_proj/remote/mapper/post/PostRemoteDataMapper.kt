package com.cheise_proj.remote.mapper.post

import com.cheise_proj.data.model.PostData
import com.cheise_proj.remote.mapper.IRemoteListMapper
import com.cheise_proj.remote.mapper.IRemoteMapper
import com.cheise_proj.remote.model.posts.PostRemote
import com.cheise_proj.remote.model.user.UserRemote.Companion.userMapper

class PostRemoteDataMapper : IRemoteMapper<PostRemote, PostData>,
    IRemoteListMapper<PostRemote, PostData> {
    override fun remoteToData(remote: PostRemote): PostData {
        return PostData(
            id = remote.id,
            description = remote.description,
            user = userMapper().remoteToData(remote.user),
            timestamp = remote.timestamp,
            skills = remote.skills,
            proposition = "${remote.proposition} proposition(s)",
            header = remote.header,
            amount = remote.amount
        )
    }

    override fun dataToRemote(data: PostData): PostRemote {
        return PostRemote(
            id = data.id,
            amount = data.amount,
            header = data.header,
            proposition = data.proposition.toIntOrNull() ?: 0,
            skills = data.skills,
            timestamp = data.timestamp,
            user = userMapper().dataToRemote(data.user),
            description = data.description
        )
    }

    override fun remoteListToData(remoteList: List<PostRemote>): List<PostData> {
        val data = arrayListOf<PostData>()
        remoteList.forEach { remote -> data.add(remoteToData(remote)) }
        return data
    }

    override fun dataListToRemote(dataList: List<PostData>): List<PostRemote> {
        val remote = arrayListOf<PostRemote>()
        dataList.forEach { data -> remote.add(dataToRemote(data)) }
        return remote
    }


}