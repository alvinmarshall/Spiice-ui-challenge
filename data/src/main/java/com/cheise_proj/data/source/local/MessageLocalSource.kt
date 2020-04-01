package com.cheise_proj.data.source.local

import com.cheise_proj.data.model.MessageData
import io.reactivex.Observable

interface MessageLocalSource {
    fun saveMessages(messageDataList: List<MessageData>)
    fun saveSentMessages(messageDataList: List<MessageData>)
    fun getMessages(): Observable<List<MessageData>>
    fun getSentMessages(): Observable<List<MessageData>>
    fun getSentMessages(identifier: String): Observable<List<MessageData>>
}