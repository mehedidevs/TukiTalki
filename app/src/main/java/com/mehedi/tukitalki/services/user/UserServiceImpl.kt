package com.mehedi.tukitalki.services.user

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mehedi.tukitalki.data.register.RequestUserRegister
import javax.inject.Inject

class UserServiceImpl @Inject constructor(private val dbRef: FirebaseDatabase) : UserService {
    override suspend fun createUser(request: RequestUserRegister): Task<Void> {

        return dbRef.reference.child("user").child(request.userId).setValue(request)
    }

    override suspend fun getUserById(userId: String): DatabaseReference {

        return dbRef.reference.child("user").child(userId)
    }
}