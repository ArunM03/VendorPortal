package com.vendorportal.app.others

import android.content.Context
import android.widget.Toast

class MyToast(val context: Context) {

    fun showToast(message : String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

}