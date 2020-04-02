package com.cheise_proj.remote.source

import com.cheise_proj.data.model.PostData
import com.cheise_proj.data.source.remote.PostRemoteSource
import com.cheise_proj.remote.INVALID_CREDENTIALS
import com.cheise_proj.remote.NO_CONNECTIVITY
import com.cheise_proj.remote.api.ApiService
import com.cheise_proj.remote.model.posts.PostDto
import com.cheise_proj.remote.model.posts.PostRemote.Companion.postMapper
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class PostRemoteSourceImpl @Inject constructor(private val apiService: ApiService) :
    PostRemoteSource {
    override fun fetchPosts(): Observable<List<PostData>> {
        return apiService.getPosts().map { t: PostDto ->
            println("post status: ${t.status}")
            postMapper().remoteListToData(t.post)
        }.onErrorResumeNext(
            Function {
                it.message?.let { msg ->
                    when {
                        msg.contains("Unable to resolve host") -> {
                            Observable.error(Throwable(NO_CONNECTIVITY))
                        }
                        msg.contains("HTTP 401") -> {
                            Observable.error(Throwable(INVALID_CREDENTIALS))
                        }
                        else -> {
                            Observable.error(Throwable(msg))
                        }
                    }
                }
            }
        )
    }
}