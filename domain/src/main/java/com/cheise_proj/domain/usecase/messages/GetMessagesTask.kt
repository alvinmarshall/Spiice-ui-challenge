package com.cheise_proj.domain.usecase.messages

import com.cheise_proj.domain.entities.message.MessageEntity
import com.cheise_proj.domain.repository.MessageRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Background
import com.cheise_proj.domain.rx.qualifier.Foreground
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * This class get user messages
 *
 * @property messageRepository
 * @constructor
 *
 * @param backgroundScheduler RxJava Schedulers
 * @param foregroundScheduler RxJava Schedulers
 */
class GetMessagesTask @Inject constructor(
    private val messageRepository: MessageRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<Nothing, List<MessageEntity>>(backgroundScheduler, foregroundScheduler) {
    override fun generateObservable(input: Nothing?): Observable<List<MessageEntity>> {
        return messageRepository.getMessages()
    }
}