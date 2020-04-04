package com.cheise_proj.presentation.utils

import android.view.View

interface InputValidation {
    fun isEditTextFilled(view: View, message: String? = null, isEmail: Boolean = false): Boolean
    fun hideKeyboard(view: View)
    fun isTextLengthGreater(view: View,count:Int,message: String?=null):Boolean
}