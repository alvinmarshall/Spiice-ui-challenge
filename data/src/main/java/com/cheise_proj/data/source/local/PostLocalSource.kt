package com.cheise_proj.data.source.local

import com.cheise_proj.data.model.PostData
import io.reactivex.Observable

interface PostLocalSource {
    fun getPosts(): Observable<List<PostData>>
    fun savePosts(postDataList: List<PostData>)
}