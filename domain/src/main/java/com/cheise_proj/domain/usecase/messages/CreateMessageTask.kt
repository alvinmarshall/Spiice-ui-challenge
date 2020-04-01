package com.cheise_proj.domain.usecase.messages

import com.cheise_proj.domain.repository.MessageRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Background
import com.cheise_proj.domain.rx.qualifier.Foreground
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * This class create a new proposition message
 *
 * @property messageRepository
 * @constructor
 *
 * @param backgroundScheduler RxJava Schedulers
 * @param foregroundScheduler RxJava Schedulers
 */
class CreateMessageTask @Inject constructor(
    private val messageRepository: MessageRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<CreateMessageTask.Params, Boolean>(backgroundScheduler, foregroundScheduler) {
    inner class Params(
        val postId: String,
        val content: String,
        val receiverEmail: String
    )

    override fun generateObservable(input: Params?): Observable<Boolean> {
        if (input == null) throw IllegalArgumentException("message params can't be null")
        with(input) {
            return messageRepository.createMessage(postId, content, receiverEmail)
        }
    }
}