package com.mehedi.tukitalki.services.auth


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.login.RequestUserLogin

interface AuthService {

    suspend fun register(request: RequestUserRegister): Task<AuthResult>

    suspend fun login(request: RequestUserLogin): Task<AuthResult>


}