package com.cheise_proj.local.db.dao

import androidx.room.*
import com.cheise_proj.local.model.MessageLocal
import com.cheise_proj.local.model.SentMessageLocal
import io.reactivex.Observable

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReceiveMessages(messageLocalList: List<MessageLocal>)

    @Query("SELECT * FROM receive_messages")
    fun getReceiveMessages(): Observable<List<MessageLocal>>

    @Query("DELETE FROM receive_messages")
    fun deleteReceiveMessages()

    @Transaction
    fun deleteAndSaveReceiveMessages(messageLocalList: List<MessageLocal>) {
        deleteReceiveMessages()
        saveReceiveMessages(messageLocalList)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSentMessages(sentMessageLocalList: List<SentMessageLocal>)

    @Query("SELECT * FROM sent_messages")
    fun getSentMessages(): Observable<List<SentMessageLocal>>

    @Query("SELECT * FROM sent_messages WHERE email = :identifier")
    fun getSentMessages(identifier:String): Observable<List<SentMessageLocal>>

    @Query("DELETE FROM SENT_MESSAGES")
    fun deleteSentMessages()

    @Transaction
    fun deleteAndSaveSentMessages(sentMessageLocalList: List<SentMessageLocal>) {
        deleteSentMessages()
        saveSentMessages(sentMessageLocalList)
    }

}