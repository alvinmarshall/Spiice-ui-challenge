package com.cheise_proj.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.cheise_proj.domain.entities.message.MessageEntity
import com.cheise_proj.domain.usecase.messages.GetMessagesTask
import com.cheise_proj.domain.usecase.messages.GetSentMessagesTask
import com.cheise_proj.presentation.model.Message
import com.cheise_proj.presentation.model.Message.Companion.messageMapper
import com.cheise_proj.presentation.vo.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class MessageViewModel @Inject constructor(
    private val getMessagesTask: GetMessagesTask,
    private val getSentMessagesTask: GetSentMessagesTask
) : BaseViewModel() {

    fun getReceiveMessages(): LiveData<Resource<List<Message>>> {
        return getMessagesTask.buildUseCase()
            .map { t: List<MessageEntity> ->
                Resource.onSuccess(
                    messageMapper().entityListToPresentation(t)
                )
            }
            .startWith(Resource.onLoading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.onError(it.localizedMessage))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }

    fun getSentMessages(): LiveData<Resource<List<Message>>> {
        return getSentMessagesTask.buildUseCase()
            .map { t: List<MessageEntity> ->
                Resource.onSuccess(
                    messageMapper().entityListToPresentation(t)
                )
            }
            .startWith(Resource.onLoading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.onError(it.localizedMessage))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }

    fun getSentMessages(identifier: String): LiveData<Resource<List<Message>>> {
        return getSentMessagesTask.buildUseCase(identifier)
            .map { t: List<MessageEntity> ->
                Resource.onSuccess(
                    messageMapper().entityListToPresentation(t)
                )
            }
            .startWith(Resource.onLoading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.onError(it.localizedMessage))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }
}