package com.cheise_proj.domain.repository

import com.cheise_proj.domain.entities.user.ProfileEntity
import com.cheise_proj.domain.entities.user.UserEntity
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

    /**
     * Get UserProfile
     *
     * @return Observable<ProfileEntity>
     */
    fun getUserProfile(): Observable<ProfileEntity>

    /**
     * Register a NewUser
     *
     * @param name user first + last name
     * @param email user email address
     * @param password user password
     */
    fun registerNewUser(name: String, email: String, password: String): Observable<Boolean>
}