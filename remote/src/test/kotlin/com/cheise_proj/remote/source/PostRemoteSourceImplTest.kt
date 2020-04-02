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
import utils.TestPostGenerator

@RunWith(JUnit4::class)
class PostRemoteSourceImplTest {
    private lateinit var postRemoteSourceImpl: PostRemoteSourceImpl

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        postRemoteSourceImpl = PostRemoteSourceImpl(apiService)
    }

    @Test
    fun `Get Posts Success`() {
        val actual = TestPostGenerator.getPostDto()
        Mockito.`when`(apiService.getPosts()).thenReturn(Observable.just(actual))
        postRemoteSourceImpl.fetchPosts()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("post Dto: $it")
                return@assertValue true
            }
            .assertComplete()
        Mockito.verify(apiService, times(1)).getPosts()
    }

    @Test
    fun `Get Posts Failed`() {
        val actual = NO_CONNECTIVITY
        Mockito.`when`(apiService.getPosts()).thenReturn(Observable.error(Throwable(actual)))
        postRemoteSourceImpl.fetchPosts()
            .test()
            .assertSubscribed()
            .assertError {
                println("posts error: $it")
                return@assertError it.message == actual
            }
            .assertNotComplete()
    }
}