package com.cheise_proj.local.source

import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.data.model.UserData
import com.cheise_proj.data.source.local.UserLocalSource
import com.cheise_proj.local.db.dao.UserDao
import com.cheise_proj.local.model.ProfileLocal
import com.cheise_proj.local.model.ProfileLocal.Companion.profileMapper
import com.cheise_proj.local.model.ProfileLocal.Companion.reviewMapper
import com.cheise_proj.local.model.ReviewsLocal
import com.cheise_proj.local.model.UserLocal
import com.cheise_proj.local.model.UserLocal.Companion.userMapper
import io.reactivex.Single
import javax.inject.Inject

class UserLocalSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalSource {
    override fun getUserProfile(): Single<ProfileData> {
        println("getting profile")
        return userDao.getReviews()
            .map { t: List<ReviewsLocal> -> reviewMapper().localListToData(t) }
            .singleOrError().onErrorResumeNext {
                Single.just(arrayListOf())
            }
            .flatMap {
                userDao.getProfile().map { t: ProfileLocal ->
                    val data = profileMapper().localToData(t)
                    data.reviews = it
                    return@map data
                }
            }
    }

    override fun getAuthUser(email: String, password: String): Single<UserData> {
        println("getting user")
        return userDao.getUser(email).map { t: UserLocal -> userMapper().localToData(t) }
    }

    override fun saveUser(userData: UserData) {
        println("saving user")
        userDao.saveUser(userMapper().dataToLocal(userData))
    }

    override fun saveProfile(profileData: ProfileData) {
        println("saving profile")
        userDao.deleteAndSaveProfile(profileMapper().dataToLocal(profileData))
    }
}