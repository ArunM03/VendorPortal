package com.vendorportal.app.data

data class LoginResponseData(
    val token : String,
    val `data`: String,
    val message: String,
    val status: Int
)