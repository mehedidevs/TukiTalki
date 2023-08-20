package com.mehedi.tukitalki.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.mehedi.tukitalki.data.RequestUserRegister
import com.mehedi.tukitalki.services.AuthServiceImpl
import javax.inject.Inject

class AuthRepo @Inject constructor(private var authService: AuthServiceImpl) {


    suspend fun registration(requestUserRegister: RequestUserRegister): Task<AuthResult> {

        return authService.register(requestUserRegister)

    }


}