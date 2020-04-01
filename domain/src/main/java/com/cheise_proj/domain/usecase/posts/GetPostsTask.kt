package com.cheise_proj.domain.usecase.posts

import com.cheise_proj.domain.entities.posts.PostEntity
import com.cheise_proj.domain.repository.PostRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Background
import com.cheise_proj.domain.rx.qualifier.Foreground
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * This class get all available posts
 *
 * @property postRepository Repository Interface
 * @constructor
 *
 * @param backgroundScheduler RxJava Schedulers
 * @param foregroundScheduler RxJava Schedulers
 */
class GetPostsTask @Inject constructor(
    private val postRepository: PostRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<Nothing, List<PostEntity>>(backgroundScheduler, foregroundScheduler) {
    override fun generateSingle(input: Nothing?): Observable<List<PostEntity>> {
        return postRepository.getPosts()
    }
}