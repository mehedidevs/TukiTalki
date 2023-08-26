package com.mehedi.tukitalki.services.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.login.RequestUserLogin
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AuthService {


    override suspend fun register(request: RequestUserRegister): Task<AuthResult> {
        return this.auth.createUserWithEmailAndPassword(request.email, request.password)
    }

    override suspend fun login(request: RequestUserLogin): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(request.email, request.password)
    }


}