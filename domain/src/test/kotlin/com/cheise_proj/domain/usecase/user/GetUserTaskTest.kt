package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import utils.TestUserGenerator

@RunWith(JUnit4::class)
class GetUserTaskTest {
    companion object {
        private const val email = "test email"
        private const val password = "test secure password"
    }

    private lateinit var getUserTask: GetUserTask

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserTask = GetUserTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `Get Authenticated User Data Success`() {
        val actual = TestUserGenerator.getUser()
        Mockito.`when`(userRepository.getAuthUser(email, password))
            .thenReturn(Observable.just(actual))
        getUserTask.buildUseCase(getUserTask.Params(email, password))
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
        Mockito.verify(userRepository, times(1)).getAuthUser(email, password)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Get Authenticated  User  With No Params  Failed`() {
        getUserTask.buildUseCase()
    }
}