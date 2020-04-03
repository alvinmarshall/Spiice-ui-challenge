package com.cheise_proj.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.cheise_proj.domain.entities.posts.PostEntity
import com.cheise_proj.domain.usecase.posts.GetPostsTask
import com.cheise_proj.presentation.model.Post
import com.cheise_proj.presentation.model.Post.Companion.postMapper
import com.cheise_proj.presentation.vo.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val getPostsTask: GetPostsTask
) : BaseViewModel() {

    fun getPosts(): LiveData<Resource<List<Post>>> {
        return getPostsTask.buildUseCase()
            .map { t: List<PostEntity> ->
                Resource.onSuccess(postMapper().entityListToPresentation(t))
            }
            .startWith(Resource.onLoading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.onError(it.localizedMessage))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }
}