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
import org.mockito.MockitoAnnotations
import utils.TestMessageGenerator

@RunWith(JUnit4::class)
class GetMessagesTaskTest {
    private lateinit var getMessagesTask: GetMessagesTask

    @Mock
    lateinit var messageRepository: MessageRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getMessagesTask =
            GetMessagesTask(messageRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `Get User Messages Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRepository.getMessages()).thenReturn(Observable.just(actual))
        getMessagesTask.buildUseCase()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
    }
}