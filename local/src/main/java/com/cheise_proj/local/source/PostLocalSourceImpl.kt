package com.cheise_proj.local.source

import com.cheise_proj.data.model.PostData
import com.cheise_proj.data.source.local.PostLocalSource
import com.cheise_proj.local.db.dao.PostDao
import com.cheise_proj.local.model.PostLocal
import com.cheise_proj.local.model.PostLocal.Companion.postMapper
import io.reactivex.Observable
import javax.inject.Inject

class PostLocalSourceImpl @Inject constructor(
    private val postDao: PostDao
) : PostLocalSource {
    override fun getPosts(): Observable<List<PostData>> {
        println("getting posts")
        return postDao.getPosts().map { t: List<PostLocal> -> postMapper().localListToData(t) }
    }

    override fun savePosts(postDataList: List<PostData>) {
        println("saving posts")
        postDao.deleteAndSavePosts(postMapper().dataListListToLocal(postDataList))
    }
}