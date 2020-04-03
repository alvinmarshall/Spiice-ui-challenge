package com.cheise_proj.presentation.job

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.cheise_proj.domain.usecase.messages.CreateMessageTask
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class SendMessageWorker @Inject constructor(
    appContext: Context, workerParams: WorkerParameters,
    private val createMessageTask: CreateMessageTask
) :
    RxWorker(appContext, workerParams) {
    override fun createWork(): Single<Result> {
        val postId = inputData.getString("postId") ?: ""
        val content = inputData.getString("content") ?: ""
        val receiverEmail = inputData.getString("receiverEmail") ?: ""
        return createMessageTask.buildUseCase(
            createMessageTask.Params(
                postId,
                content,
                receiverEmail
            )
        ).lastOrError()
            .map { isSuccess ->
                Timber.i("Send Message Status: $isSuccess")
                Result.success()
            }
    }
}
