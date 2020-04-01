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

@RunWith(JUnit4::class)
class CreateMessageTaskTest {
    companion object {
        private const val isSuccess = true
        private const val postId = "post uid"
        private const val content = "message content"
        private const val receiverEmail = "recipient email"
    }

    private lateinit var createMessageTask: CreateMessageTask

    @Mock
    lateinit var messageRepository: MessageRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        createMessageTask =
            CreateMessageTask(messageRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `Create Proposition Message Success`() {
        val actual = isSuccess
        Mockito.`when`(messageRepository.createMessage(postId, content, receiverEmail)).thenReturn(
            Observable.just(actual)
        )
        createMessageTask.buildUseCase(createMessageTask.Params(postId, content, receiverEmail))
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
        Mockito.verify(messageRepository, times(1)).createMessage(postId, content, receiverEmail)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Create Proposition Message Failed`() {
        createMessageTask.buildUseCase()
    }
}