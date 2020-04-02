package com.cheise_proj.data.repository

import com.cheise_proj.data.isFailure
import com.cheise_proj.data.isSuccess
import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.data.model.ProfileData.Companion.profileMapper
import com.cheise_proj.data.model.UserData
import com.cheise_proj.data.model.UserData.Companion.userMapper
import com.cheise_proj.data.source.local.UserLocalSource
import com.cheise_proj.data.source.remote.UserRemoteSource
import com.cheise_proj.domain.entities.user.ProfileEntity
import com.cheise_proj.domain.entities.user.UserEntity
import com.cheise_proj.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemote: UserRemoteSource,
    private val userLocal: UserLocalSource
) : UserRepository {
    override fun getAuthUser(email: String, password: String): Observable<UserEntity> {
        val local = userLocal.getAuthUser(email, password)
            .map { t: UserData -> userMapper().dataToEntity(t) }.toObservable()
        return userRemote.fetchAuthUser(email, password).map { t: UserData ->
            userLocal.saveUser(t)
            return@map userMapper().dataToEntity(t)
        }.concatWith(local)
    }

    override fun getUserProfile(): Observable<ProfileEntity> {
        val local =
            userLocal.getUserProfile().map { t: ProfileData -> profileMapper().dataToEntity(t) }
                .toObservable()
        return userRemote.fetchUserProfile().map { t: ProfileData ->
            userLocal.saveProfile(t)
            profileMapper().dataToEntity(t)
        }.concatWith(local)
    }

    override fun registerNewUser(
        name: String,
        email: String,
        password: String
    ): Observable<Boolean> {
        return userRemote.registerUser(name, email, password).map { t: UserData ->
            if (t.accessToken.isBlank()) return@map isFailure
            userLocal.saveUser(t)
            return@map isSuccess
        }
    }
}