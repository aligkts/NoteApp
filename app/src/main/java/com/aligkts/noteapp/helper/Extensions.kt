package com.aligkts.noteapp.helper

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


infix fun View.hideKeyboard(context: Context) {

    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


