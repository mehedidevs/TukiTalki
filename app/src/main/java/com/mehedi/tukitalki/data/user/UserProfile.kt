package com.mehedi.tukitalki.data.user



data class UserProfile(var uId: String?=null,
                       var createdAt: Long?=null,
                       var updatedAt: Long?=null,
                       var image: String="",
                       var name: String="",
                       var email: String="",
                       var about: String="",
                       var token :String="",
                       var mobile: String?=null)
