package com.mehedi.tukitalki.services.user


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.login.RequestUserLogin

interface UserService {
    suspend fun createUser(request: RequestUserRegister): Task<Void>


}