package com.cheise_proj.domain.rx

import com.cheise_proj.domain.usecase.base.BaseUseCase
import io.reactivex.Scheduler
import io.reactivex.Single


abstract class SingleUseCase<T, in Params>(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler
) : BaseUseCase(backgroundScheduler, foregroundScheduler) {
    protected abstract fun generateSingle(input: Params?): Single<T>
    /**
     * Subscribe to single build case
     *
     * @param input type input
     * @return subscribe to generate single<T>
     */
    fun buildUseCase(input: Params? = null): Single<T> = generateSingle(input)
        .subscribeOn(backgroundScheduler)
        .observeOn(foregroundScheduler)
}