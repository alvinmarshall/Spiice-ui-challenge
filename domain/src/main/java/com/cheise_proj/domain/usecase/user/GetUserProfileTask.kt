package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.entities.ProfileEntity
import com.cheise_proj.domain.repository.UserRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Background
import com.cheise_proj.domain.rx.qualifier.Foreground
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetUserProfileTask(
    private val userRepository: UserRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<Nothing, ProfileEntity>(backgroundScheduler, foregroundScheduler) {
    override fun generateSingle(input: Nothing?): Observable<ProfileEntity> {
        return userRepository.getUserProfile()
    }
}