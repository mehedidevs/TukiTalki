package com.mehedi.tukitalki.services.user

import android.net.Uri
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.user.UserProfile
import com.mehedi.tukitalki.utils.asMap
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val dbRef: FirebaseDatabase,
) : UserService {

    override suspend fun createUser(request: RequestUserRegister): Task<Void> {

        return dbRef.reference.child("user").child(request.userId).setValue(request)
    }

    override suspend fun getUserById(userId: String): DatabaseReference {

        return dbRef.reference.child("user").child(userId)
    }

    override suspend fun getUserAllUser(): DatabaseReference {


        return dbRef.reference.child("user")


    }

    override suspend fun updateUser(request: UserProfile): Task<Void> {
        val userMap = request.asMap()

        return dbRef.reference.child("user").child(request.userId).updateChildren(userMap)
    }

    override suspend fun uploadProfileImage(uri: Uri, strRef: StorageReference): UploadTask {

        return strRef.putFile(uri)


    }
}