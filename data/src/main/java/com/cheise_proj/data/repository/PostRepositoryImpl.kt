package com.cheise_proj.data.repository

import com.cheise_proj.data.model.PostData
import com.cheise_proj.data.model.PostData.Companion.postMapper
import com.cheise_proj.data.source.local.PostLocalSource
import com.cheise_proj.data.source.remote.PostRemoteSource
import com.cheise_proj.domain.entities.posts.PostEntity
import com.cheise_proj.domain.repository.PostRepository
import io.reactivex.Observable
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteSource: PostRemoteSource,
    private val postLocalSource: PostLocalSource
) : PostRepository {
    override fun getPosts(): Observable<List<PostEntity>> {
        val local =
            postLocalSource.getPosts().map { t: List<PostData> -> postMapper().dataListToEntity(t) }
        return postRemoteSource.fetchPosts()
            .map { t: List<PostData> ->
                postLocalSource.savePosts(t)
                postMapper().dataListToEntity(t)
            }.mergeWith(local)
    }
}