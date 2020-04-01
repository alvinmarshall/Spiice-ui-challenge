package com.cheise_proj.data.source.local

import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.data.model.UserData
import io.reactivex.Observable
import io.reactivex.Single

interface UserLocalSource {
    fun getUserProfile(): Single<ProfileData>
    fun getAuthUser(email: String, password: String): Single<UserData>
    fun saveUser(userData: UserData)
    fun saveProfile(profileData: ProfileData)
}