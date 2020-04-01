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
import java.lang.IllegalArgumentException

@RunWith(JUnit4::class)
class RegisterUserTaskTest {
    companion object {
        private const val name = "first last name"
        private const val email = "email address"
        private const val password = "secure password"
        private const val isSuccessful = true
    }

    private lateinit var registerUserTask: RegisterUserTask

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        registerUserTask =
            RegisterUserTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `Register New User Success`() {
        val actual = isSuccessful
        Mockito.`when`(userRepository.registerNewUser(name, email, password))
            .thenReturn(Observable.just(actual))
        registerUserTask.buildUseCase(registerUserTask.Params(name, email, password))
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
        Mockito.verify(userRepository, times(1))
            .registerNewUser(name, email, password)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Register User Failed`() {
        registerUserTask.buildUseCase()
    }
}