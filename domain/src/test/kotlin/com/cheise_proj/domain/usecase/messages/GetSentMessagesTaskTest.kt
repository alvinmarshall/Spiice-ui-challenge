package com.cheise_proj.domain.usecase.messages

import com.cheise_proj.domain.repository.MessageRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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
class GetSentMessagesTaskTest {
    companion object {
        private const val receiverEmail = "receiver email"
    }

    private lateinit var getSentMessagesTask: GetSentMessagesTask

    @Mock
    lateinit var messageRepository: MessageRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getSentMessagesTask =
            GetSentMessagesTask(messageRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `Get Sent Messages Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRepository.getSentMessages()).thenReturn(Observable.just(actual))
        getSentMessagesTask.buildUseCase()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
        Mockito.verify(messageRepository, times(1)).getSentMessages()
        Mockito.verify(messageRepository, times(0)).getSentMessages(receiverEmail)
    }

    @Test
    fun `Get Sent Messages With Params Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRepository.getSentMessages(receiverEmail))
            .thenReturn(Observable.just(actual))
        getSentMessagesTask.buildUseCase(receiverEmail)
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
        Mockito.verify(messageRepository, times(1)).getSentMessages(receiverEmail)
        Mockito.verify(messageRepository, times(0)).getSentMessages()
    }


}