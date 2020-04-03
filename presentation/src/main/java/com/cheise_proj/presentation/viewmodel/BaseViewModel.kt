package com.cheise_proj.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val disposable: CompositeDisposable = CompositeDisposable()
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}