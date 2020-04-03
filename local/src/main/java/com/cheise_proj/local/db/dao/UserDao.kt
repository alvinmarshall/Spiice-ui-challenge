package com.cheise_proj.local.db.dao

import androidx.room.*
import com.cheise_proj.local.model.ProfileLocal
import com.cheise_proj.local.model.ReviewsLocal
import com.cheise_proj.local.model.UserLocal
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userLocal: UserLocal)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun getUser(email: String): Single<UserLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProfile(profileLocal: ProfileLocal)

    @Query("SELECT * FROM profile")
    fun getProfile(): Single<ProfileLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReviews(reviewsLocalList: List<ReviewsLocal>)

    @Query("SELECT * FROM reviews")
    fun getReviews(): Observable<List<ReviewsLocal>>

    @Query("DELETE FROM receive_messages")
    fun deleteReviews()

    @Query("DELETE FROM profile")
    fun deleteProfile()

    @Transaction
    fun deleteAndSaveProfile(profileLocal: ProfileLocal) {
        deleteProfile()
        deleteReviews()
        saveProfile(profileLocal)
        saveReviews(profileLocal.reviews)
    }
}