package com.cheise_proj.remote.mapper.user

import com.cheise_proj.data.model.ReviewsData
import com.cheise_proj.remote.mapper.IRemoteListMapper
import com.cheise_proj.remote.mapper.IRemoteMapper
import com.cheise_proj.remote.model.user.ReviewRemote
import com.cheise_proj.remote.model.user.UserRemote.Companion.userMapper

class ReviewRemoteDataMapper : IRemoteMapper<ReviewRemote, ReviewsData>,
    IRemoteListMapper<ReviewRemote, ReviewsData> {
    override fun remoteToData(remote: ReviewRemote): ReviewsData {
        return ReviewsData(
            content = remote.content,
            timestamp = remote.timestamp,
            rating = remote.rating,
            sender = userMapper().remoteToData(remote.sender),
            id = remote.id
        )
    }

    override fun dataToRemote(data: ReviewsData): ReviewRemote {
        return ReviewRemote(
            id = data.id,
            content = data.content,
            timestamp = data.timestamp,
            rating = data.rating,
            sender = userMapper().dataToRemote(data.sender)
        )
    }

    override fun remoteListToData(remoteList: List<ReviewRemote>): List<ReviewsData> {
        val data = arrayListOf<ReviewsData>()
        remoteList.forEach { remote -> data.add(remoteToData(remote)) }
        return data
    }

    override fun dataListToRemote(dataList: List<ReviewsData>): List<ReviewRemote> {
        val remote = arrayListOf<ReviewRemote>()
        dataList.forEach { data -> remote.add(dataToRemote(data)) }
        return remote
    }

}
