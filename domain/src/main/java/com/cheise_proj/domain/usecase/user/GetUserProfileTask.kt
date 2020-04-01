package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.entities.user.ProfileEntity
import com.cheise_proj.domain.repository.UserRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Background
import com.cheise_proj.domain.rx.qualifier.Foreground
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * This class get authenticated user profile
 *
 *  class implements ObservableUseCase class
 *
 * @property userRepository
 * @constructor
 *
 * @param backgroundScheduler RxJava Schedulers
 * @param foregroundScheduler RxJava Schedulers
 */
class GetUserProfileTask @Inject constructor(
    private val userRepository: UserRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<Nothing, ProfileEntity>(backgroundScheduler, foregroundScheduler) {
    override fun generateObservable(input: Nothing?): Observable<ProfileEntity> {
        return userRepository.getUserProfile()
    }
}