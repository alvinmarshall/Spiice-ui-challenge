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
import utils.TestUserGenerator

@RunWith(JUnit4::class)
class UserRemoteSourceImplTest {
    companion object {
        private const val name = "first + last name "
        private const val email = "email address"
        private const val password = "secure password"
    }

    private lateinit var userRemoteSourceImpl: UserRemoteSourceImpl

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userRemoteSourceImpl = UserRemoteSourceImpl(apiService)
    }

    //region User Profile
    @Test
    fun `Get User Profile Success`() {
        val actual = TestUserGenerator.getProfileDto()
        Mockito.`when`(apiService.getUserProfile()).thenReturn(Observable.just(actual))
        userRemoteSourceImpl.fetchUserProfile()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("profile Dto : $it")
                return@assertValue true
            }
            .assertComplete()
        Mockito.verify(apiService, times(1)).getUserProfile()
    }

    @Test
    fun `Get User Profile Failed`() {
        val actual = NO_CONNECTIVITY
        Mockito.`when`(apiService.getUserProfile()).thenReturn(
            Observable.error(
                Throwable(actual)
            )
        )
        userRemoteSourceImpl.fetchUserProfile()
            .test()
            .assertSubscribed()
            .assertError {
                println("user profile error: $it")
                return@assertError it.message == actual
            }
            .assertNotComplete()
    }

    //endregion

    //region Authenticate User
    @Test
    fun `Authenticate User Success`() {
        val actual = TestUserGenerator.getUserDto()
        Mockito.`when`(apiService.authenticateUser(email, password))
            .thenReturn(Observable.just(actual))
        userRemoteSourceImpl.fetchAuthUser(email, password)
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("User Dto :$it")
                return@assertValue true
            }
            .assertComplete()
    }

    @Test
    fun `Authenticate User Failed`() {
        val actual = NO_CONNECTIVITY
        Mockito.`when`(apiService.authenticateUser(email, password)).thenReturn(
            Observable.error(
                Throwable(actual)
            )
        )
        userRemoteSourceImpl.fetchAuthUser(email, password)
            .test()
            .assertSubscribed()
            .assertError {
                println("authenticate error: $it")
                return@assertError it.message == actual
            }
            .assertNotComplete()
    }

    //endregion


    //region Register User
    @Test
    fun `Register New User Success`() {
        val actual = TestUserGenerator.getUserDto()
        Mockito.`when`(apiService.registerUser(name, email, password))
            .thenReturn(Observable.just(actual))
        userRemoteSourceImpl.registerUser(name, email, password)
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("User Dto $it")
                return@assertValue true
            }
            .assertComplete()
    }

    @Test
    fun `Register New User Failed`() {
        val actual = NO_CONNECTIVITY
        Mockito.`when`(apiService.registerUser(name, email, password)).thenReturn(
            Observable.error(
                Throwable(actual)
            )
        )
        userRemoteSourceImpl.registerUser(name, email, password)
            .test()
            .assertSubscribed()
            .assertError {
                println("register error: $it")
                return@assertError it.message == actual
            }
            .assertNotComplete()
    }

    //endregion


}