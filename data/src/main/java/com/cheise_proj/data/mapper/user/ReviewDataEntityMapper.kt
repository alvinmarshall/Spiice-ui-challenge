package com.cheise_proj.data.mapper.user

import com.cheise_proj.data.mapper.IDataListMapper
import com.cheise_proj.data.mapper.IDataMapper
import com.cheise_proj.data.model.ReviewsData
import com.cheise_proj.domain.entities.user.Reviews

class ReviewDataEntityMapper : IDataMapper<ReviewsData, Reviews>,
    IDataListMapper<ReviewsData, Reviews> {
    override fun dataToEntity(data: ReviewsData): Reviews {
        return Reviews(
            content = data.content,
            timestamp = data.timestamp,
            rating = data.rating
        )
    }

    override fun entityToData(entity: Reviews): ReviewsData {
        return ReviewsData(
            content = entity.content,
            timestamp = entity.timestamp,
            rating = entity.rating
        )
    }

    override fun dataListToEntity(dataList: List<ReviewsData>): List<Reviews> {
        val entity = arrayListOf<Reviews>()
        dataList.forEach { data -> entity.add(dataToEntity(data)) }
        return entity
    }

    override fun entityListToData(entityList: List<Reviews>): List<ReviewsData> {
        val data = arrayListOf<ReviewsData>()
        entityList.forEach { entity -> data.add(entityToData(entity)) }
        return data
    }
}