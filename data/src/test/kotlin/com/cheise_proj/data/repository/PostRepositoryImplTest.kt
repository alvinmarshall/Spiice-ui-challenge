package com.cheise_proj.data.repository

import com.cheise_proj.data.source.local.PostLocalSource
import com.cheise_proj.data.source.remote.PostRemoteSource
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
class PostRepositoryImplTest {
    private lateinit var postRepositoryImpl: PostRepositoryImpl

    @Mock
    lateinit var postRemoteSource: PostRemoteSource

    @Mock
    lateinit var postLocalSource: PostLocalSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        postRepositoryImpl = PostRepositoryImpl(postRemoteSource, postLocalSource)
    }

    @Test
    fun `Get Posts Success`() {
        val actual = TestPostGenerator.getPosts()
        Mockito.`when`(postLocalSource.getPosts()).thenReturn(Observable.just(actual))
        Mockito.`when`(postRemoteSource.fetchPosts()).thenReturn(Observable.just(actual))
        postRepositoryImpl.getPosts()
            .test()
            .assertSubscribed()
            .assertComplete()
        Mockito.verify(postLocalSource, times(1)).savePosts(actual)
        Mockito.verify(postLocalSource, times(1)).getPosts()
        Mockito.verify(postRemoteSource, times(1)).fetchPosts()

    }
}