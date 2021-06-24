package com.udacity.shoestore

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

object Converter {

    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    @JvmStatic
    fun getDouble(text: EditText) =
        text.text.toString().let { value ->
            if (value.isEmpty()) return 0.0
            else value.toDouble()
        }


    @BindingAdapter("android:text")
    @JvmStatic
    fun setDouble(text: EditText, value: Double) {
        text.setText(value.toString())
    }
}