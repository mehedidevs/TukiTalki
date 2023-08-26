package com.mehedi.tukitalki.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.login.RequestUserLogin
import com.mehedi.tukitalki.services.auth.AuthServiceImpl
import javax.inject.Inject

class AuthRepo @Inject constructor (private var authService: AuthServiceImpl) {




    suspend fun registration(requestUserRegister: RequestUserRegister): Task<AuthResult> {
        return authService.register(requestUserRegister)
    }


    suspend fun login(request: RequestUserLogin): Task<AuthResult> {
        return authService.login(request)
    }


}