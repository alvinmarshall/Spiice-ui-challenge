package com.cheise_proj.spiice_ui_challenge.di.module.workmanager

import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.cheise_proj.presentation.job.SendMessageWorker
import com.cheise_proj.spiice_ui_challenge.di.key.WorkerKey
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Subcomponent(modules = [WorkerSubComponent.Binders::class])
interface WorkerSubComponent {

    fun workers(): Map<Class<out RxWorker>, Provider<RxWorker>>

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun workerParameters(param: WorkerParameters): Builder

        fun build(): WorkerSubComponent
    }

    @Module
    interface Binders {

        @Binds
        @IntoMap
        @WorkerKey(SendMessageWorker::class)
        fun bindSendMessageWorker(sendMessageWorker: SendMessageWorker): RxWorker

    }
}