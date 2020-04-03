package com.cheise_proj.local.db.dao

import androidx.room.*
import com.cheise_proj.local.model.PostLocal
import io.reactivex.Observable

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePosts(postLocalList: List<PostLocal>)

    @Query("SELECT * FROM posts")
    fun getPosts(): Observable<List<PostLocal>>

    @Query("DELETE FROM posts")
    fun deletePosts()

    @Transaction
    fun deleteAndSavePosts(postLocalList: List<PostLocal>) {
        deletePosts()
        savePosts(postLocalList)
    }
}