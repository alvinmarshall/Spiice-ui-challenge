package com.cheise_proj.data.source.remote

import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.data.model.UserData
import io.reactivex.Observable

interface UserRemoteSource {
    fun fetchUserProfile(): Observable<ProfileData>
    fun fetchAuthUser(email: String, password: String): Observable<UserData>
    fun registerUser(name: String, email: String, password: String): Observable<UserData>
}