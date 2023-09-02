package com.mehedi.tukitalki.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DatabaseReference
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.login.RequestUserLogin
import com.mehedi.tukitalki.services.auth.AuthServiceImpl
import com.mehedi.tukitalki.services.user.UserService
import com.mehedi.tukitalki.services.user.UserServiceImpl
import javax.inject.Inject

class UserRepo @Inject constructor(private var service: UserServiceImpl) {


    suspend fun createUser(requestUserRegister: RequestUserRegister): Task<Void> {

        return service.createUser(requestUserRegister)

    }

    suspend fun getUserById(userId: String): DatabaseReference {
        return service.getUserById(userId)

    }

    suspend fun getAllUser(): DatabaseReference {
        return service.getUserAllUser()

    }


}