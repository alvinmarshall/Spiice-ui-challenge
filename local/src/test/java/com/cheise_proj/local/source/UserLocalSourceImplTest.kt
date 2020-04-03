package com.cheise_proj.local.source

import com.cheise_proj.local.db.dao.UserDao
import com.cheise_proj.local.model.ProfileLocal.Companion.profileMapper
import com.cheise_proj.local.model.UserLocal.Companion.userMapper
import com.cheise_proj.local.utils.TestUserGenerator
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

@RunWith(JUnit4::class)
class UserLocalSourceImplTest {
    companion object {
        private const val name = "first + last name"
        private const val email = "email address"
        private const val password = "secure password"
    }

    private lateinit var userLocalSourceImpl: UserLocalSourceImpl

    @Mock
    lateinit var userDao: UserDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userLocalSourceImpl = UserLocalSourceImpl(userDao)
    }

    @Test
    fun `Get Authenticated User Success`() {
        val actual = TestUserGenerator.getUser()
        Mockito.`when`(userDao.getUser(email)).thenReturn(Single.just(actual))
        userLocalSourceImpl.getAuthUser(email, password)
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("user local: $it")
                return@assertValue true
            }
            .assertComplete()
    }

    @Test
    fun `Get Profile Success`() {
        val actual = TestUserGenerator.getProfile()
        Mockito.`when`(userDao.getProfile()).thenReturn(Single.just(actual))
        Mockito.`when`(userDao.getReviews()).thenReturn(Observable.just(actual.reviews))
        userLocalSourceImpl.getUserProfile()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("profile local: $it")
                return@assertValue it.reviews.size == actual.reviews.size
            }
            .assertComplete()
        Mockito.verify(userDao,times(1)).getProfile()
        Mockito.verify(userDao,times(1)).getReviews()
    }

    @Test
    fun `Get Review With Errors Return Empty`() {
        val actual = TestUserGenerator.getProfile()
        val errorMsg = "An error occurred"
        Mockito.`when`(userDao.getProfile()).thenReturn(Single.just(actual))
        Mockito.`when`(userDao.getReviews()).thenReturn(Observable.error(Throwable(errorMsg)))
        userLocalSourceImpl.getUserProfile()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("profile local: $it")
                return@assertValue it.reviews.isEmpty()
            }
            .assertComplete()
        Mockito.verify(userDao,times(1)).getProfile()
        Mockito.verify(userDao,times(1)).getReviews()
    }



    @Test
    fun `Save Authenticated User Success`() {
        val actual = TestUserGenerator.getUser()
        userLocalSourceImpl.saveUser(userMapper().localToData(actual))
        Mockito.verify(userDao,times(1)).saveUser(actual)
    }

    @Test
    fun `Save Profile Success`() {
        val actual = TestUserGenerator.getProfile()
        userLocalSourceImpl.saveProfile(profileMapper().localToData(actual))
        Mockito.verify(userDao,times(1)).deleteAndSaveProfile(actual)
    }
}