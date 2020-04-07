package com.cheise_proj.remote.api

import com.cheise_proj.remote.model.StatusDto
import com.cheise_proj.remote.model.message.MessageDto
import com.cheise_proj.remote.model.posts.PostDto
import com.cheise_proj.remote.model.token.TokenDto
import com.cheise_proj.remote.model.user.ProfileDto
import com.cheise_proj.remote.model.user.UserDto
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //region refresh token
    @FormUrlEncoded
    @POST("users/refresh_token")
    fun getNewAccessToken(@Field("refresh_token") refreshToken: String): Call<TokenDto>
    //endregion

    //region User
    @FormUrlEncoded
    @POST("users/signin")
    fun authenticateUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<UserDto>

    @GET("users/profile")
    fun getUserProfile(): Observable<ProfileDto>

    @FormUrlEncoded
    @POST("users/signup")
    fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<UserDto>

    //endregion

    //region Messages
    @GET("messages/receiver")
    fun getReceiveMessages(): Observable<MessageDto>

    @GET("messages/sent")
    fun getSentMessages(): Observable<MessageDto>

    @FormUrlEncoded
    @POST("messages")
    fun sendMessage(
        @Field("receiverEmail") receiverEmail: String,
        @Field("postId") postId: String,
        @Field("content") content: String
    ): Observable<StatusDto>
    //endregion

    //region Posts
    @GET("posts")
    fun getPosts(): Observable<PostDto>
    //endregion
}