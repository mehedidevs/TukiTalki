package com.mehedi.tukitalki.data

data class RequestUserRegister(
    var userId: String = "",
    val name: String,
    val email: String,
    val password: String
)
