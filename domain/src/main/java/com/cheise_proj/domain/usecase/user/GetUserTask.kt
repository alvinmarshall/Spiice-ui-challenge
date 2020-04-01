package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.entities.UserEntity
import com.cheise_proj.domain.repository.UserRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Background
import com.cheise_proj.domain.rx.qualifier.Foreground
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * The GetUserTask authenticate the user and get their profile data
 *
 * @constructor
 *
 * @param backgroundScheduler provide RxJava background Schedulers
 * @param foregroundScheduler provide RxJava UI Scheduler
 */
class GetUserTask @Inject constructor(
    private val userRepository: UserRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<GetUserTask.Params, UserEntity>(backgroundScheduler, foregroundScheduler) {
    inner class Params(val email: String, val password: String)

    override fun generateSingle(input: Params?): Observable<UserEntity> {
        if (input == null) throw IllegalArgumentException("authentication params can't be null")
        with(input) {
            return userRepository.getAuthUser(email, password)
        }
    }
}