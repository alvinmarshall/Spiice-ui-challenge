package com.cheise_proj.local.source

import com.cheise_proj.local.db.dao.MessageDao
import com.cheise_proj.local.model.MessageLocal.Companion.messageMapper
import com.cheise_proj.local.model.SentMessageLocal.Companion.sentMessageMapper
import com.cheise_proj.local.utils.TestMessageGenerator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MessageLocalSourceImplTest {
    companion object {
        private const val identifier = "email address"
    }

    private lateinit var messageLocalSourceImpl: MessageLocalSourceImpl

    @Mock
    lateinit var messageDao: MessageDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        messageLocalSourceImpl = MessageLocalSourceImpl(messageDao)
    }

    @Test
    fun `Get Receive Message Success`() {
        val actual = TestMessageGenerator.getReceiveMessages()
        Mockito.`when`(messageDao.getReceiveMessages()).thenReturn(Observable.just(actual))
        messageLocalSourceImpl.getMessages()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("receive message local: $it")
                return@assertValue true
            }
            .assertComplete()
    }

    @Test
    fun `Get Sent Message Success`() {
        val actual = TestMessageGenerator.getSentMessages()
        Mockito.`when`(messageDao.getSentMessages()).thenReturn(Observable.just(actual))
        messageLocalSourceImpl.getSentMessages()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("sent message local: $it")
                return@assertValue true
            }
            .assertComplete()
    }

    @Test
    fun `Get Sent Message With Identifier Success`() {
        val actual = TestMessageGenerator.getSentMessages()
        Mockito.`when`(messageDao.getSentMessages(identifier)).thenReturn(Observable.just(actual))
        messageLocalSourceImpl.getSentMessages(identifier)
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("sent message with identifier local: $it")
                return@assertValue true
            }
            .assertComplete()
    }

    @Test
    fun `Save Receive Message Success`() {
        val actual = TestMessageGenerator.getReceiveMessages()
        messageLocalSourceImpl.saveMessages(messageMapper().localListToData(actual))
        Mockito.verify(messageDao, times(1)).deleteAndSaveReceiveMessages(actual)
    }

    @Test
    fun `Save Sent Message Success`() {
        val actual = TestMessageGenerator.getSentMessages()
        messageLocalSourceImpl.saveSentMessages(sentMessageMapper().localListToData(actual))
        Mockito.verify(messageDao, times(1)).deleteAndSaveSentMessages(actual)
    }
}