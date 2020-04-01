package com.cheise_proj.data.mapper.post

import com.cheise_proj.data.mapper.IDataListMapper
import com.cheise_proj.data.mapper.IDataMapper
import com.cheise_proj.data.model.PostData
import com.cheise_proj.data.model.UserData.Companion.userMapper
import com.cheise_proj.domain.entities.posts.PostEntity

class PostDataEntityMapper : IDataMapper<PostData, PostEntity>,
    IDataListMapper<PostData, PostEntity> {
    override fun dataToEntity(data: PostData): PostEntity {
        return PostEntity(
            id = data.id,
            timestamp = data.timestamp,
            description = data.description,
            user = userMapper().dataToEntity(data.user),
            amount = data.amount,
            header = data.header,
            proposition = data.proposition,
            skills = data.skills
        )
    }

    override fun entityToData(entity: PostEntity): PostData {
        return PostData(
            id = entity.id,
            timestamp = entity.timestamp,
            description = entity.description,
            user = userMapper().entityToData(entity.user),
            amount = entity.amount,
            header = entity.header,
            proposition = entity.proposition,
            skills = entity.skills
        )
    }

    override fun dataListToEntity(dataList: List<PostData>): List<PostEntity> {
        val entity = arrayListOf<PostEntity>()
        dataList.forEach { data -> entity.add(dataToEntity(data)) }
        return entity
    }

    override fun entityListToData(entityList: List<PostEntity>): List<PostData> {
        val data = arrayListOf<PostData>()
        entityList.forEach { entity -> data.add(entityToData(entity)) }
        return data
    }
}