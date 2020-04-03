package com.cheise_proj.presentation.vo

enum class STATUS {
    LOADING, SUCCESS, ERROR
}

data class Resource<out T>(val status: STATUS, val data: T?, val message: String?) {
    companion object {
        fun <T> onLoading(): Resource<T> {
            return Resource(STATUS.LOADING, null, null)
        }

        fun <T> onSuccess(data: T, message: String? = null): Resource<T> {
            return Resource(STATUS.SUCCESS, data, message)
        }

        fun <T> onError(message: String?, data: T? = null): Resource<T> {
            return Resource(STATUS.ERROR, data, message)
        }
    }

}