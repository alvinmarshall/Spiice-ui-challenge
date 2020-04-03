package com.cheise_proj.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cheise_proj.domain.repository.PostRepository
import com.cheise_proj.domain.usecase.posts.GetPostsTask
import com.cheise_proj.presentation.model.Post.Companion.postMapper
import com.cheise_proj.presentation.utils.TestPostGenerator
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
class PostViewModelTest {
    private lateinit var postViewModel: PostViewModel
    private lateinit var getPostsTask: GetPostsTask

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getPostsTask =
            GetPostsTask(postRepository, Schedulers.trampoline(), Schedulers.trampoline())
        postViewModel = PostViewModel(getPostsTask)
    }

    @Test
    fun `Get Posts Success`() {
        val actual = TestPostGenerator.getPosts()
        Mockito.`when`(postRepository.getPosts())
            .thenReturn(Observable.just(postMapper().presentationListToEntity(actual)))
        val postLive = postViewModel.getPosts()
        postLive.observeForever { }
        assertTrue(
            postLive.value?.status == STATUS.SUCCESS
        )
        println("posts: ${postLive.value?.data}")
    }

    @Test
    fun `Get Posts Failed`() {
        val errorMsg = "An error occurred"
        Mockito.`when`(postRepository.getPosts()).thenReturn(Observable.error(Throwable(errorMsg)))
        val postLive = postViewModel.getPosts()
        postLive.observeForever {  }
        assertTrue(
            postLive.value?.status == STATUS.ERROR
        )
        println("post error: ${postLive.value?.message}")
    }
}