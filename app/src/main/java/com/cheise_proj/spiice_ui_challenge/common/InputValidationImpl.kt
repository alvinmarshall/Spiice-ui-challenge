package com.cheise_proj.spiice_ui_challenge.common

import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.cheise_proj.presentation.utils.InputValidation
import com.cheise_proj.spiice_ui_challenge.R
import javax.inject.Inject

class InputValidationImpl @Inject constructor() : InputValidation {
    override fun isEditTextFilled(view: View, message: String?, isEmail: Boolean): Boolean {
        val editText = view as EditText
        val value = editText.text.toString().trim()
        if (value.isEmpty()) {
            editText.error = message ?: view.context.getString(R.string.error_empty_input)
            return false
        }
        if (isEmail) {
            return if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
                editText.error = message ?: view.context.getString(R.string.error_invalid_email)
                false
            } else true
        }
        return true
    }

    override fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}