package com.cheise_proj.domain.repository

import com.cheise_proj.domain.entities.posts.PostEntity
import io.reactivex.Observable

interface PostRepository {
    /**
     * Get all Posts
     *
     * @return Observable<PostEntity>
     */
    fun getPosts(): Observable<List<PostEntity>>
}