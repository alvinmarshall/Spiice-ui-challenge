package com.cheise_proj.local.source

import com.cheise_proj.local.db.dao.PostDao
import com.cheise_proj.local.model.PostLocal.Companion.postMapper
import com.cheise_proj.local.utils.TestPostGenerator
import io.reactivex.Observable
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class PostLocalSourceImplTest {

    private lateinit var postLocalSourceImpl: PostLocalSourceImpl

    @Mock
    lateinit var postDao: PostDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        postLocalSourceImpl = PostLocalSourceImpl(postDao)
    }

    @Test
    fun `Get Posts Success`() {
        val actual = TestPostGenerator.getPosts()
        Mockito.`when`(postDao.getPosts()).thenReturn(Observable.just(actual))
        postLocalSourceImpl.getPosts()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                println("post local: $it")
                return@assertValue true
            }
            .assertComplete()
    }

    @Test
    fun `Save Post Success`() {
        val actual = TestPostGenerator.getPosts()
        postLocalSourceImpl.savePosts(postMapper().localListToData(actual))
        Mockito.verify(postDao,times(1)).deleteAndSavePosts(actual)
    }
}