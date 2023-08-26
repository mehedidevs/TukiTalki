package com.mehedi.tukitalki.data.register

data class RequestUserRegister(
    var userId: String = "",
    val name: String,
    val password: String,
    var createdAt: Long?=null,
    var updatedAt: Long?=null,
    var image: String="",
    var email: String="",
    var about: String="",
    var token :String="",
    var mobile: String?=null


)
