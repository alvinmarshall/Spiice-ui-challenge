package com.cheise_proj.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cheise_proj.domain.repository.UserRepository
import com.cheise_proj.domain.usecase.user.GetUserProfileTask
import com.cheise_proj.domain.usecase.user.GetUserTask
import com.cheise_proj.domain.usecase.user.RegisterUserTask
import com.cheise_proj.presentation.model.Profile.Companion.profileMapper
import com.cheise_proj.presentation.model.User.Companion.userMapper
import com.cheise_proj.presentation.utils.TestUserGenerator
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
class UserViewModelTest {
    companion object {
        private const val name = "first + last name"
        private const val email = "email address"
        private const val password = "secure password"
        private const val errorMsg = "An error occurred"
    }

    private lateinit var userViewModel: UserViewModel
    private lateinit var getUserTask: GetUserTask
    private lateinit var getUserProfileTask: GetUserProfileTask
    private lateinit var registerUserTask: RegisterUserTask

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserTask = GetUserTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline())

        getUserProfileTask =
            GetUserProfileTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline())

        registerUserTask =
            RegisterUserTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline())

        userViewModel = UserViewModel(getUserTask, getUserProfileTask, registerUserTask)
    }

    @Test
    fun `Get Authenticated User Success`() {
        val actual = TestUserGenerator.getUser()
        Mockito.`when`(userRepository.getAuthUser(email, password))
            .thenReturn(Observable.just(userMapper().presentationToEntity(actual)))
        val userLive = userViewModel.getAuthenticatedUser(email, password)
        userLive.observeForever { }
        assertTrue(
            userLive.value?.status == STATUS.SUCCESS
        )
        println("user: ${userLive.value?.data}")

    }

    @Test
    fun `Get User Profile Success`() {
        val actual = TestUserGenerator.getProfile()
        Mockito.`when`(userRepository.getUserProfile())
            .thenReturn(Observable.just(profileMapper().presentationToEntity(actual)))
        val profileLive = userViewModel.getProfile()
        profileLive.observeForever { }
        assertTrue(
            profileLive.value?.status == STATUS.SUCCESS
        )
        println("profile: ${profileLive.value?.data}")

    }

    @Test
    fun `Register User Success`() {
        val actual = TestUserGenerator.getUser()
        Mockito.`when`(userRepository.registerNewUser(name, email, password)).thenReturn(
            Observable.just(userMapper().presentationToEntity(actual))
        )
        val userLive = userViewModel.registerUser(name, email, password)
        userLive.observeForever { }
        assertTrue(
            userLive.value?.status == STATUS.SUCCESS
        )
        println("user: ${userLive.value?.data}")
    }

    @Test
    fun `Get Authenticated User Failed`() {
        val actual = errorMsg
        Mockito.`when`(userRepository.getAuthUser(email, password)).thenReturn(
            Observable.error(
                Throwable(actual)
            )
        )
        val userLive = userViewModel.getAuthenticatedUser(email, password)
        userLive.observeForever { }
        assertTrue(
            userLive.value?.status == STATUS.ERROR
        )
        println("authenticate user error: ${userLive.value?.message}")
    }

    //region errors
    @Test
    fun `Register User Failed`() {
        val actual = errorMsg
        Mockito.`when`(userRepository.registerNewUser(name, email, password)).thenReturn(
            Observable.error(
                Throwable(actual)
            )
        )
        val userLive = userViewModel.registerUser(name, email, password)
        userLive.observeForever { }
        assertTrue(
            userLive.value?.status == STATUS.ERROR
        )
        println("register error: ${userLive.value?.message}")
    }

    @Test
    fun `Get Profile Failed`() {
        val actual = errorMsg
        Mockito.`when`(userRepository.getUserProfile()).thenReturn(
            Observable.error(
                Throwable(actual)
            )
        )
        val profileLive = userViewModel.getProfile()
        profileLive.observeForever { }
        assertTrue(
            profileLive.value?.status == STATUS.ERROR
        )
        println("profile error: ${profileLive.value?.message}")
    }
    //endregion
}