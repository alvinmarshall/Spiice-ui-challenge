package com.cheise_proj.domain.repository

import com.cheise_proj.domain.entities.UserEntity
import io.reactivex.Observable

interface UserRepository {

    /**
     * Get Authenticated User
     *
     * @param email user email
     * @param password user password
     * @return Observable<UserEntity>
     */
    fun getAuthUser(email: String, password: String): Observable<UserEntity>
}