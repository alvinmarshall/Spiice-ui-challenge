package com.cheise_proj.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cheise_proj.domain.repository.MessageRepository
import com.cheise_proj.domain.usecase.messages.GetMessagesTask
import com.cheise_proj.domain.usecase.messages.GetSentMessagesTask
import com.cheise_proj.presentation.model.Message.Companion.messageMapper
import com.cheise_proj.presentation.utils.TestMessageGenerator
import com.cheise_proj.presentation.vo.STATUS
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MessageViewModelTest {

    companion object {
        private const val identifier = "email address"
        private const val errorMsg = "An error occurred"
    }

    private lateinit var messageViewModel: MessageViewModel
    private lateinit var getMessagesTask: GetMessagesTask
    private lateinit var getSentMessagesTask: GetSentMessagesTask

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var messageRepository: MessageRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getMessagesTask =
            GetMessagesTask(messageRepository, Schedulers.trampoline(), Schedulers.trampoline())

        getSentMessagesTask =
            GetSentMessagesTask(messageRepository, Schedulers.trampoline(), Schedulers.trampoline())


        messageViewModel = MessageViewModel(getMessagesTask, getSentMessagesTask)
    }

    @Test
    fun `Get Receive Messages Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRepository.getMessages())
            .thenReturn(Observable.just(messageMapper().presentationListToEntity(actual)))

        val messageLive = messageViewModel.getReceiveMessages()
        messageLive.observeForever { }
        assertTrue(
            messageLive.value?.status == STATUS.SUCCESS
        )
        println("receive messages: ${messageLive.value?.data}")
    }

    @Test
    fun `Get sent Messages Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRepository.getSentMessages())
            .thenReturn(Observable.just(messageMapper().presentationListToEntity(actual)))

        val messageLive = messageViewModel.getSentMessages()
        messageLive.observeForever { }
        assertTrue(
            messageLive.value?.status == STATUS.SUCCESS
        )
        println("sent messages: ${messageLive.value?.data}")
    }

    @Test
    fun `Get sent Messages With Identifier Success`() {
        val actual = TestMessageGenerator.getUserMessages()
        Mockito.`when`(messageRepository.getSentMessages(identifier))
            .thenReturn(Observable.just(messageMapper().presentationListToEntity(actual)))

        val messageLive = messageViewModel.getSentMessages(identifier)
        messageLive.observeForever { }
        assertTrue(
            messageLive.value?.status == STATUS.SUCCESS
        )
        println("sent messages with identifier: ${messageLive.value?.data}")
    }

    //region errors
    @Test
    fun `Get Receive Messages Failed`() {
        val actual = errorMsg
        Mockito.`when`(messageRepository.getMessages()).thenReturn(
            Observable.error(
                Throwable(
                    actual
                )
            )
        )
        val messageLive = messageViewModel.getReceiveMessages()
        messageLive.observeForever { }
        assertTrue(
            messageLive.value?.status == STATUS.ERROR
        )
        println("receive message error: ${messageLive.value?.message}")
    }

    @Test
    fun `Get Sent Messages Failed`() {
        val actual = errorMsg
        Mockito.`when`(messageRepository.getSentMessages()).thenReturn(
            Observable.error(
                Throwable(
                    actual
                )
            )
        )
        val messageLive = messageViewModel.getSentMessages()
        messageLive.observeForever { }
        assertTrue(
            messageLive.value?.status == STATUS.ERROR
        )
        println("sent message error: ${messageLive.value?.message}")
    }

    @Test
    fun `Get Sent Messages With Identifier Failed`() {
        val actual = errorMsg
        Mockito.`when`(messageRepository.getSentMessages(identifier)).thenReturn(
            Observable.error(
                Throwable(
                    actual
                )
            )
        )
        val messageLive = messageViewModel.getSentMessages(identifier)
        messageLive.observeForever { }
        assertTrue(
            messageLive.value?.status == STATUS.ERROR
        )
        println("sent message with identifier error: ${messageLive.value?.message}")
    }


    //endregion


}