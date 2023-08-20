package com.mehedi.tukitalki.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.tukitalki.data.RequestUserRegister
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AuthService {


    override suspend fun register(request: RequestUserRegister): Task<AuthResult> {
        return this.auth.createUserWithEmailAndPassword(request.email, request.password)
    }


}