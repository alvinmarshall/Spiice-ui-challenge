package com.cheise_proj.remote.source

import com.cheise_proj.data.model.MessageData
import com.cheise_proj.data.source.remote.MessageRemoteSource
import com.cheise_proj.remote.INVALID_CREDENTIALS
import com.cheise_proj.remote.NO_CONNECTIVITY
import com.cheise_proj.remote.api.ApiService
import com.cheise_proj.remote.model.StatusDto
import com.cheise_proj.remote.model.message.MessageDto
import com.cheise_proj.remote.model.message.MessageRemote.Companion.messageMapper
import io.reactivex.Observable
import io.reactivex.functions.Function
import java.net.HttpURLConnection
import javax.inject.Inject

class MessageRemoteSourceImpl @Inject constructor(private val apiService: ApiService) :
    MessageRemoteSource {
    override fun createMessage(
        postId: String,
        content: String,
        receiverEmail: String
    ): Observable<Boolean> {
        return apiService.sendMessage(receiverEmail, postId, content).map { t: StatusDto ->
            println("send message status: ${t.status}")
            return@map t.status == HttpURLConnection.HTTP_CREATED
        }.onErrorResumeNext(
            Function {
                it.message?.let { msg ->
                    when {
                        msg.contains("Unable to resolve host") -> {
                            Observable.error(Throwable(NO_CONNECTIVITY))
                        }
                        msg.contains("HTTP 401") -> {
                            Observable.error(Throwable(INVALID_CREDENTIALS))
                        }
                        else -> {
                            Observable.error(Throwable(msg))
                        }
                    }
                }
            }
        )
    }

    override fun fetchMessages(): Observable<List<MessageData>> {
        return apiService.getReceiveMessages().map { t: MessageDto ->
            println("messages status: ${t.status}")
            messageMapper().remoteListToData(t.message)
        }.onErrorResumeNext(
            Function {
                it.message?.let { msg ->
                    when {
                        msg.contains("Unable to resolve host") -> {
                            Observable.error(Throwable(NO_CONNECTIVITY))
                        }
                        msg.contains("HTTP 401") -> {
                            Observable.error(Throwable(INVALID_CREDENTIALS))
                        }
                        else -> {
                            Observable.error(Throwable(msg))
                        }
                    }
                }
            }
        )
    }

    override fun fetchSentMessages(): Observable<List<MessageData>> {
        return apiService.getSentMessages().map { t: MessageDto ->
            println("sent messages status: ${t.status}")
            messageMapper().remoteListToData(t.message)
        }.onErrorResumeNext(
            Function {
                it.message?.let { msg ->
                    when {
                        msg.contains("Unable to resolve host") -> {
                            Observable.error(Throwable(NO_CONNECTIVITY))
                        }
                        msg.contains("HTTP 401") -> {
                            Observable.error(Throwable(INVALID_CREDENTIALS))
                        }
                        else -> {
                            Observable.error(Throwable(msg))
                        }
                    }
                }
            }
        )
    }
}