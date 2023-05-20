package ru.app.fastestdelivery.util.ui.inputState

import android.content.Context
import android.content.res.ColorStateList
import android.widget.EditText
import ru.app.fastestdelivery.R

fun EditText.setDefaultState(context: Context) {
    setHintTextColor(context.getColor(R.color.main_grey))
    backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.main_grey));
}

fun EditText.setErrorState(context: Context) {
    setHintTextColor(context.getColor(R.color.main_red))
    backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.main_red));
}