package com.udacity.shoestore.data

import android.content.Context

class PreferenceManager(
    context: Context?
) {

    companion object {
        private const val PREFERENCE_LOGIN = "PreferenceLogin"
        private const val HAS_LOGIN = "has_login"
    }

    private val sharedPreference =
        context?.getSharedPreferences(PREFERENCE_LOGIN, Context.MODE_PRIVATE)

    fun hasLogged() = sharedPreference?.getBoolean(HAS_LOGIN, false) ?: false

    fun saveLogin() = statusLogin(true)

    fun resetLogin() = statusLogin(false)

    private fun statusLogin(status: Boolean) {
        sharedPreference?.edit()?.apply {
            putBoolean(HAS_LOGIN, status)
            apply()
        }
    }

}