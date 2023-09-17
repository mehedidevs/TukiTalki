package com.mehedi.tukitalki.repositories

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.login.RequestUserLogin
import com.mehedi.tukitalki.data.user.UserProfile
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

    suspend fun updateUser(request: UserProfile): Task<Void> {

        return service.updateUser(request)
    }


    suspend fun uploadProfileImage(uri: Uri, strRef: StorageReference): UploadTask {

        return service.uploadProfileImage(uri, strRef)

    }


}