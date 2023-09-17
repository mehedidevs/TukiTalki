package com.mehedi.tukitalki.services.user


import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.login.RequestUserLogin
import com.mehedi.tukitalki.data.user.UserProfile

interface UserService {
    suspend fun createUser(request: RequestUserRegister): Task<Void>
    suspend fun getUserById(userId: String): DatabaseReference
    suspend fun getUserAllUser(): DatabaseReference
    suspend fun updateUser(request: UserProfile): Task<Void>

    suspend fun uploadProfileImage(uri: Uri,strRef: StorageReference): UploadTask


}