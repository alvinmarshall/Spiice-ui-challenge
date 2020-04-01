package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.repository.UserRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Background
import com.cheise_proj.domain.rx.qualifier.Foreground
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * This class register's a new user
 *
 * class implements ObservableUseCase class
 *
 * @property userRepository
 * @constructor
 *
 * @param backgroundScheduler RxJava Schedulers
 * @param foregroundScheduler RxJava Schedulers
 */
class RegisterUserTask @Inject constructor(
    private val userRepository: UserRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<RegisterUserTask.Params, Boolean>(backgroundScheduler, foregroundScheduler) {
    inner class Params(val name: String, val email: String, val password: String)

    override fun generateObservable(input: Params?): Observable<Boolean> {
        if (input == null) throw IllegalArgumentException("register params can't be null")
        with(input) {
            return userRepository.registerNewUser(name, email, password)
        }
    }
}