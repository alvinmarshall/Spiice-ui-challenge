package com.cheise_proj.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.cheise_proj.domain.entities.user.ProfileEntity
import com.cheise_proj.domain.entities.user.UserEntity
import com.cheise_proj.domain.usecase.user.GetUserProfileTask
import com.cheise_proj.domain.usecase.user.GetUserTask
import com.cheise_proj.domain.usecase.user.RegisterUserTask
import com.cheise_proj.presentation.model.Profile
import com.cheise_proj.presentation.model.Profile.Companion.profileMapper
import com.cheise_proj.presentation.model.User
import com.cheise_proj.presentation.model.User.Companion.userMapper
import com.cheise_proj.presentation.vo.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserTask: GetUserTask,
    private val getUserProfileTask: GetUserProfileTask,
    private val registerUserTask: RegisterUserTask
) : BaseViewModel() {
    fun getAuthenticatedUser(email: String, password: String): LiveData<Resource<User>> {
        return getUserTask.buildUseCase(getUserTask.Params(email, password))
            .map { t: UserEntity ->
                Resource.onSuccess(userMapper().entityToPresentation(t))
            }
            .startWith(Resource.onLoading())
            .onErrorResumeNext(Function {
                Observable.just(Resource.onError(it.localizedMessage))
            })
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }

    fun getProfile(): LiveData<Resource<Profile>> {
        return getUserProfileTask.buildUseCase()
            .map { t: ProfileEntity ->
                Resource.onSuccess(profileMapper().entityToPresentation(t))
            }
            .startWith(Resource.onLoading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.onError(it.localizedMessage))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }

    fun registerUser(name:String,email:String,password: String):LiveData<Resource<User>>{
        return registerUserTask.buildUseCase(registerUserTask.Params(name,email, password))
            .map { t: UserEntity ->
                Resource.onSuccess(userMapper().entityToPresentation(t))
            }
            .startWith(Resource.onLoading())
            .onErrorResumeNext(Function {
                Observable.just(Resource.onError(it.localizedMessage))
            })
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()

    }

}