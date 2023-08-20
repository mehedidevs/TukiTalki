package com.mehedi.tukitalki.services


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.mehedi.tukitalki.data.RequestUserRegister

interface AuthService {

    suspend fun register(request: RequestUserRegister): Task<AuthResult>


}