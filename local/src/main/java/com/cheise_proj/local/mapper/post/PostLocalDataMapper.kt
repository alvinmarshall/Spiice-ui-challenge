package com.cheise_proj.local.mapper.post

import com.cheise_proj.data.model.PostData
import com.cheise_proj.local.mapper.ILocalListMapper
import com.cheise_proj.local.mapper.ILocalMapper
import com.cheise_proj.local.model.PostLocal
import com.cheise_proj.local.model.UserLocal.Companion.userMapper

class PostLocalDataMapper : ILocalMapper<PostLocal, PostData>,
    ILocalListMapper<PostLocal, PostData> {
    override fun localToData(local: PostLocal): PostData {
        return PostData(
            id = local.id,
            description = local.description,
            user = userMapper().localToData(local.user),
            timestamp = local.timestamp,
            skills = local.skills,
            proposition = local.proposition,
            header = local.header,
            amount = local.amount
        )
    }

    override fun dataToLocal(data: PostData): PostLocal {
        return PostLocal(
            id = data.id,
            amount = data.amount,
            header = data.header,
            proposition = data.proposition,
            timestamp = data.timestamp,
            user = userMapper().dataToLocal(data.user),
            description = data.description
        ).apply {
            skills = data.skills
        }


    }

    override fun localListToData(localList: List<PostLocal>): List<PostData> {
        val data = arrayListOf<PostData>()
        localList.forEach { local -> data.add(localToData(local)) }
        return data
    }

    override fun dataListListToLocal(dataToLocalList: List<PostData>): List<PostLocal> {
        val remote = arrayListOf<PostLocal>()
        dataToLocalList.forEach { data -> remote.add(dataToLocal(data)) }
        return remote
    }
}


