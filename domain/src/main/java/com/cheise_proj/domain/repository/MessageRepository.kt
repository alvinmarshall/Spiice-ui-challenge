package com.cheise_proj.domain.repository

import com.cheise_proj.domain.entities.message.MessageEntity
import io.reactivex.Observable

interface MessageRepository {
    /**
     * Get UserMessages
     *
     * @return Observable<List<MessageEntity>>
     */
    fun getMessages(): Observable<List<MessageEntity>>

    fun getSentMessages(): Observable<List<MessageEntity>>
    fun getSentMessages(receiverEmail: String): Observable<List<MessageEntity>>

    /**
     * Create a proposition Message
     *
     * @param postId current post id
     * @param content user message
     * @param receiverEmail current post user email
     * @return Observable<Boolean>
     */
    fun createMessage(postId: String, content: String, receiverEmail: String): Observable<Boolean>
}