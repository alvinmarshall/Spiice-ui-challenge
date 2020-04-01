package com.cheise_proj.data.source.remote

import com.cheise_proj.data.model.PostData
import io.reactivex.Observable

interface PostRemoteSource {
    fun fetchPosts(): Observable<List<PostData>>
}