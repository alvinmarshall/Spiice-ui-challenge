package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.repository.UserRepository
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
import utils.TestUserGenerator

@RunWith(JUnit4::class)
class GetUserProfileTaskTest {
    private lateinit var getUserProfileTask: GetUserProfileTask

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserProfileTask =
            GetUserProfileTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `Get User Profile Success`() {
        val actual = TestUserGenerator.getProfile()
        Mockito.`when`(userRepository.getUserProfile()).thenReturn(Observable.just(actual))
        getUserProfileTask.buildUseCase()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
        Mockito.verify(userRepository, times(1)).getUserProfile()
    }
}