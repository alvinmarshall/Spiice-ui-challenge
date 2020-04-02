package com.cheise_proj.remote.source

import com.cheise_proj.remote.NO_CONNECTIVITY
import com.cheise_proj.remote.api.ApiService
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
class MessageRemoteSourceImplTest {
    companion object {
        private const val postId = "post uid"
        private const val content = "message content"
        private const val receiverEmail = "receiver email address"
    }

    private lateinit var messageRemoteSourceImpl: MessageRemoteSourceImpl

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        messageRemoteSourceImpl = MessageRemoteSourceImpl(apiService)
    }

    //region Messages
    @Test
    fun `Get Messages Success`() {
        val actual = TestMessageGenerator.getMessageDto()
        Mockito.`when`(apiService.getReceiveMessages()).thenReturn(Observable.just(actual))
        messageRemoteSourceImpl.fetchMessages()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("message Dto: $it")
                return@assertValue true
            }
            .assertComplete()
        Mockito.verify(apiService, times(1)).getReceiveMessages()
    }

    @Test
    fun `Get Messages Failed`() {
        val actual = NO_CONNECTIVITY
        Mockito.`when`(apiService.getReceiveMessages())
            .thenReturn(Observable.error(Throwable(actual)))
        messageRemoteSourceImpl.fetchMessages()
            .test()
            .assertSubscribed()
            .assertError {
                println("messages error: $it")
                return@assertError it.message == actual
            }
            .assertNotComplete()
    }

    //endregion

    //region Sent Messages
    @Test
    fun `Get Sent Messages Success`() {
        val actual = TestMessageGenerator.getMessageDto()
        Mockito.`when`(apiService.getSentMessages()).thenReturn(Observable.just(actual))
        messageRemoteSourceImpl.fetchSentMessages()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("sent message Dto: $it")
                return@assertValue true
            }
            .assertComplete()
        Mockito.verify(apiService, times(1)).getSentMessages()
    }

    @Test
    fun `Get sent Messages Failed`() {
        val actual = NO_CONNECTIVITY
        Mockito.`when`(apiService.getSentMessages())
            .thenReturn(Observable.error(Throwable(actual)))
        messageRemoteSourceImpl.fetchSentMessages()
            .test()
            .assertSubscribed()
            .assertError {
                println("sent messages error: $it")
                return@assertError it.message == actual
            }
            .assertNotComplete()
        Mockito.verify(apiService, times(1)).getSentMessages()
    }

    //endregion

    //region Send Message
    @Test
    fun `Send Message Success`() {
        val actual = TestMessageGenerator.getStatusDto()
        Mockito.`when`(apiService.sendMessage(receiverEmail, postId, content))
            .thenReturn(Observable.just(actual))
        messageRemoteSourceImpl.createMessage(postId, content, receiverEmail)
            .test()
            .assertSubscribed()
            .assertValue {
                println("send message: $it")
                return@assertValue true
            }
            .assertComplete()
    }

    @Test
    fun `Send Message Failed`() {
        val actual = NO_CONNECTIVITY
        Mockito.`when`(apiService.sendMessage(receiverEmail, postId, content)).thenReturn(
            Observable.error(
                Throwable(actual)
            )
        )
        messageRemoteSourceImpl.createMessage(postId, content, receiverEmail)
            .test()
            .assertSubscribed()
            .assertError {
                println("send message error: $it")
                return@assertError it.message == actual
            }
            .assertNotComplete()
    }

    //endregion
}