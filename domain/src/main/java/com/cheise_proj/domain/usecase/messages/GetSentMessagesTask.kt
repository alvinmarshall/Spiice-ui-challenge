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
 * This class get user sent messages
 *class implements ObservableUseCase
 * @property messageRepository
 * @constructor
 *
 * @param backgroundScheduler RxJava Schedulers
 * @param foregroundScheduler RxJava Schedulers
 */
class GetSentMessagesTask @Inject constructor(
    private val messageRepository: MessageRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<String, List<MessageEntity>>(backgroundScheduler, foregroundScheduler) {
    override fun generateObservable(input: String?): Observable<List<MessageEntity>> {
        if (input == null) return messageRepository.getSentMessages()
        return messageRepository.getSentMessages(input)
    }
}