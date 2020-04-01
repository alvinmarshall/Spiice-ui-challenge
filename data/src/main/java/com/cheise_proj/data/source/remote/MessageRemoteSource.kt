package com.cheise_proj.data.source.remote

import com.cheise_proj.data.model.MessageData
import io.reactivex.Observable

interface MessageRemoteSource {
    fun createMessage(
        postId: String,
        content: String,
        receiverEmail: String
    ): Observable<Boolean>

    fun fetchMessages(): Observable<List<MessageData>>
    fun fetchSentMessages(): Observable<List<MessageData>>
}