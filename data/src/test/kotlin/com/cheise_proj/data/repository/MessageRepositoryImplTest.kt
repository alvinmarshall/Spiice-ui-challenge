package com.cheise_proj.data.repository

import com.cheise_proj.data.source.local.MessageLocalSource
import com.cheise_proj.data.source.remote.MessageRemoteSource
import io.reactivex.Observable
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import utils.TestMessageGenerator

@RunWith(JUnit4::class)
class MessageRepositoryImplTest {
    companion object {
        private const val isSuccess = true
        private const val receiverEmail = "recipient email"
        private const val postId = "post uid"
        private const val content = "proposition message"
    }

    private lateinit var messageRepositoryImpl: MessageRepositoryImpl

    @Mock
    lateinit var messageLocalSource: MessageLocalSource

    @Mock
    lateinit var messageRemoteSource: MessageRemoteSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        messageRepositoryImpl = MessageRepositoryImpl(messageRemoteSource, messageLocalSource)
    }

    @Test
    fun `Get Messages Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRemoteSource.fetchMessages()).thenReturn(Observable.just(actual))
        Mockito.`when`(messageLocalSource.getMessages()).thenReturn(Observable.just(actual))
        messageRepositoryImpl.getMessages()
            .test()
            .assertSubscribed()
            .assertComplete()

        Mockito.verify(messageLocalSource, times(1)).getMessages()
        Mockito.verify(messageRemoteSource, times(1)).fetchMessages()
        Mockito.verify(messageLocalSource, times(1)).saveMessages(actual)
    }

    @Test
    fun `Get Sent Messages Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRemoteSource.fetchSentMessages()).thenReturn(Observable.just(actual))
        Mockito.`when`(messageLocalSource.getSentMessages()).thenReturn(Observable.just(actual))
        messageRepositoryImpl.getSentMessages()
            .test()
            .assertSubscribed()
            .assertComplete()

        Mockito.verify(messageLocalSource, times(1)).getSentMessages()
        Mockito.verify(messageRemoteSource, times(1)).fetchSentMessages()
        Mockito.verify(messageLocalSource, times(1)).saveSentMessages(actual)
    }

    @Test
    fun `Get Sent Messages With Identifier Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRemoteSource.fetchSentMessages()).thenReturn(Observable.just(actual))
        Mockito.`when`(messageLocalSource.getSentMessages(receiverEmail))
            .thenReturn(Observable.just(actual))
        messageRepositoryImpl.getSentMessages(receiverEmail)
            .test()
            .assertSubscribed()
            .assertComplete()

        Mockito.verify(messageLocalSource, times(1)).getSentMessages(receiverEmail)
        Mockito.verify(messageRemoteSource, times(1)).fetchSentMessages()
        Mockito.verify(messageLocalSource, times(1)).saveSentMessages(actual)
    }

    @Test
    fun `Create New Message Success`() {
        val actual = isSuccess
        Mockito.`when`(messageRemoteSource.createMessage(postId, content, receiverEmail))
            .thenReturn(
                Observable.just(actual)
            )
        messageRepositoryImpl.createMessage(postId, content, receiverEmail)
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
        Mockito.verify(messageRemoteSource, times(1)).createMessage(postId, content, receiverEmail)
    }
}