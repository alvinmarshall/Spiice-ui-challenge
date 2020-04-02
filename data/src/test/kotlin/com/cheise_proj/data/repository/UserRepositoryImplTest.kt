package com.cheise_proj.data.repository

import com.cheise_proj.data.source.local.UserLocalSource
import com.cheise_proj.data.source.remote.UserRemoteSource
import io.reactivex.Observable
import io.reactivex.Single
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
class UserRepositoryImplTest {
    companion object {
        private const val name = "first + last name"
        private const val email = "email address"
        private const val password = "secure password"
    }

    private lateinit var userRepositoryImpl: UserRepositoryImpl

    @Mock
    lateinit var userRemoteSource: UserRemoteSource

    @Mock
    lateinit var userLocalSource: UserLocalSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userRepositoryImpl = UserRepositoryImpl(userRemoteSource, userLocalSource)
    }

    @Test
    fun `Register User Remotely Success`() {
        val actual = TestUserGenerator.getUser()
        Mockito.`when`(userRemoteSource.registerUser(name, email, password))
            .thenReturn(Observable.just(actual))
        userRepositoryImpl.registerNewUser(name, email, password)
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertComplete()
        Mockito.verify(userRemoteSource, times(1)).registerUser(name, email, password)
    }

    @Test
    fun `Get User Profile Success`() {
        val actual = TestUserGenerator.getProfile()
        Mockito.`when`(userLocalSource.getUserProfile()).thenReturn(Single.just(actual))
        Mockito.`when`(userRemoteSource.fetchUserProfile()).thenReturn(Observable.just(actual))
        userRepositoryImpl.getUserProfile()
            .test()
            .assertSubscribed()
            .assertValueCount(2)
            .assertComplete()
        Mockito.verify(userRemoteSource, times(1)).fetchUserProfile()
        Mockito.verify(userLocalSource, times(1)).getUserProfile()
    }

    @Test
    fun `Authenticate User Success`() {
        val actual = TestUserGenerator.getUser()
        Mockito.`when`(userRemoteSource.fetchAuthUser(email, password))
            .thenReturn(Observable.just(actual))
        Mockito.`when`(userLocalSource.getAuthUser(email, password)).thenReturn(Single.just(actual))
        userRepositoryImpl.getAuthUser(email, password)
            .test()
            .assertSubscribed()
            .assertComplete()
        Mockito.verify(userLocalSource, times(1)).saveUser(actual)
        Mockito.verify(userLocalSource, times(1)).getAuthUser(email, password)
        Mockito.verify(userRemoteSource, times(1)).fetchAuthUser(email, password)
    }
}