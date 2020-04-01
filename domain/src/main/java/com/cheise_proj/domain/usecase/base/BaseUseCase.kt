package com.cheise_proj.domain.usecase.base

import io.reactivex.Scheduler

abstract class BaseUseCase constructor(
    protected val backgroundScheduler: Scheduler,
    protected val foregroundScheduler: Scheduler
)