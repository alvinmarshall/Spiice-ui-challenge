package com.cheise_proj.domain.usecase.posts

import com.cheise_proj.domain.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import utils.TestPostGenerator

@RunWith(JUnit4::class)
class GetPostsTaskTest {

    private lateinit var getPostsTask: GetPostsTask

    @Mock
    lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getPostsTask =
            GetPostsTask(postRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `Get All Posts Success`() {
        val actual = TestPostGenerator.getPosts()
        Mockito.`when`(postRepository.getPosts()).thenReturn(Observable.just(actual))
    }
}