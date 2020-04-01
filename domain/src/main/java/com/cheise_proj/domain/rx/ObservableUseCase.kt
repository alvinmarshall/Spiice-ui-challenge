package com.cheise_proj.domain.rx

import com.cheise_proj.domain.usecase.base.BaseUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * The ObservableUseCase abstract class implements BaseUseCase
 *which subscribes to the use case classes
 * @param Params any
 * @param T any
 *
 *
 * @param backgroundScheduler RxJava Schedulers
 * @param foregroundScheduler RxJava Schedulers
 */
abstract class ObservableUseCase<in Params, T>(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler
) :
    BaseUseCase(backgroundScheduler, foregroundScheduler) {
    protected abstract fun generateObservable(input: Params?): Observable<T>

    /**
     * buildUseCase subscribes to the observable type
     *
     * @param input
     * @return
     */
    fun buildUseCase(input: Params? = null): Observable<T> = generateObservable(input)
        .subscribeOn(backgroundScheduler)
        .observeOn(foregroundScheduler)

}