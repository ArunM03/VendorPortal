package com.vendorportal.app.sharedpref

import android.content.Context
import kotlin.math.log

class SharedPref(private val context: Context) {

    val sharedPref = context.getSharedPreferences("vendorportal_pref", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()

    val USERAUTHSTATUS = "UserAuthStatus"
    val LOGINCODE = "LoginCode"
    val PASSWORD = "Password"
    val TOKEN  = "Token"


    fun setUserAuthStatus(status : Boolean){
        editor.apply {
            putBoolean(USERAUTHSTATUS,status)
            apply()
        }
    }

    fun saveLoginCode(loginCode : String) {
        editor.apply {
            putString(LOGINCODE, loginCode)
            apply()
        }
    }

    fun getLoginCode() : String?{
        return sharedPref.getString(LOGINCODE,"")
    }

    fun savePassword(password : String) {
        editor.apply {
            putString(PASSWORD, password)
            apply()
        }
    }

    fun getPassword() : String?{
        return sharedPref.getString(PASSWORD,"")
    }

    fun saveToken(token : String) {
        editor.apply {
            putString(TOKEN, token)
            apply()
        }
    }

    fun getToken() : String?{
        return sharedPref.getString(TOKEN,"")
    }


    fun getUserAuthStatus() : Boolean{
        return sharedPref.getBoolean(USERAUTHSTATUS,false)
    }

}