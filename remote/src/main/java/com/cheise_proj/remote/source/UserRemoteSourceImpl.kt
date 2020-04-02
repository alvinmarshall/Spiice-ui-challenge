package com.cheise_proj.remote.source

import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.data.model.UserData
import com.cheise_proj.data.source.remote.UserRemoteSource
import com.cheise_proj.remote.INVALID_CREDENTIALS
import com.cheise_proj.remote.NO_CONNECTIVITY
import com.cheise_proj.remote.api.ApiService
import com.cheise_proj.remote.model.user.ProfileDto
import com.cheise_proj.remote.model.user.ProfileRemote.Companion.profileMapper
import com.cheise_proj.remote.model.user.UserDto
import com.cheise_proj.remote.model.user.UserRemote.Companion.userMapper
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class UserRemoteSourceImpl @Inject constructor(private val apiService: ApiService) :
    UserRemoteSource {

    override fun fetchUserProfile(): Observable<ProfileData> {
        return apiService.getUserProfile().map { t: ProfileDto ->
            println("profile status: ${t.status}")
            profileMapper().remoteToData(t.profileRemote)
        }.onErrorResumeNext(
            Function {
                it.message?.let { msg ->
                    when {
                        msg.contains("Unable to resolve host") -> {
                            Observable.error(Throwable(NO_CONNECTIVITY))
                        }
                        msg.contains("HTTP 401") -> {
                            Observable.error(Throwable(INVALID_CREDENTIALS))
                        }
                        else -> {
                            Observable.error(Throwable(msg))
                        }
                    }
                }

            }
        )
    }

    override fun fetchAuthUser(email: String, password: String): Observable<UserData> {
        return apiService.authenticateUser(email, password).map { t: UserDto ->
            println("authenticate status: ${t.status}")
            t.user.accessToken = t.token
            t.user.refreshToken = t.refreshToken
            userMapper().remoteToData(t.user)
        }.onErrorResumeNext(
            Function {
                it.message?.let { msg ->
                    when {
                        msg.contains("Unable to resolve host") -> {
                            Observable.error(Throwable(NO_CONNECTIVITY))
                        }
                        msg.contains("HTTP 401") -> {
                            Observable.error(Throwable(INVALID_CREDENTIALS))
                        }
                        else -> {
                            Observable.error(Throwable(msg))
                        }
                    }
                }

            }
        )
    }

    override fun registerUser(name: String, email: String, password: String): Observable<UserData> {
        return apiService.registerUser(name, email, password).map { t: UserDto ->
            println("register status: ${t.status}")

            if (t.status == HttpsURLConnection.HTTP_CREATED) {
                t.user.accessToken = t.token
                t.user.refreshToken = t.refreshToken
                userMapper().remoteToData(t.user)
            }
            return@map userMapper().remoteToData(t.user)
        }
    }
}