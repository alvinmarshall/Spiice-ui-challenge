package com.cheise_proj.local.mapper.user

import com.cheise_proj.data.model.ReviewsData
import com.cheise_proj.local.mapper.ILocalListMapper
import com.cheise_proj.local.mapper.ILocalMapper
import com.cheise_proj.local.model.ReviewsLocal
import com.cheise_proj.local.model.UserLocal.Companion.userMapper


class ReviewLocalDataMapper : ILocalMapper<ReviewsLocal, ReviewsData>,
    ILocalListMapper<ReviewsLocal, ReviewsData> {
    override fun localToData(local: ReviewsLocal): ReviewsData {
        return ReviewsData(
            content = local.content,
            timestamp = local.timestamp,
            rating = local.rating,
            sender = userMapper().localToData(local.sender),
            id = local.id
        )
    }

    override fun dataToLocal(data: ReviewsData): ReviewsLocal {
        return ReviewsLocal(
            id = data.id,
            content = data.content,
            timestamp = data.timestamp,
            rating = data.rating,
            sender = userMapper().dataToLocal(data.sender)
        )
    }

    override fun localListToData(localList: List<ReviewsLocal>): List<ReviewsData> {
        val data = arrayListOf<ReviewsData>()
        localList.forEach { local -> data.add(localToData(local)) }
        return data
    }

    override fun dataListListToLocal(dataToLocalList: List<ReviewsData>): List<ReviewsLocal> {
        val remote = arrayListOf<ReviewsLocal>()
        dataToLocalList.forEach { data -> remote.add(dataToLocal(data)) }
        return remote
    }


}
