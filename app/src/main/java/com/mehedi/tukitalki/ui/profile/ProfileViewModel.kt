package com.mehedi.tukitalki.ui.profile

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageReference
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.user.UserProfile
import com.mehedi.tukitalki.repositories.AuthRepo
import com.mehedi.tukitalki.repositories.UserRepo
import com.mehedi.tukitalki.utils.ErrorMessage
import com.mehedi.tukitalki.utils.SuccessMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val storageRef: StorageReference
) : ViewModel() {

    private var _resposne = MutableLiveData<UserProfile>()
    val responseUserProfile: LiveData<UserProfile> = _resposne


    fun getUserById(userId: String) {


        viewModelScope.launch {

            userRepo.getUserById(userId).addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val value = snapshot.getValue(UserProfile::class.java)

                    value?.let {

                        _resposne.postValue(it)
                    }


                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }

            })


        }


    }


    private var _responseAllUser = MutableLiveData<List<UserProfile>>()
    val responseAllUserProfile: LiveData<List<UserProfile>> = _responseAllUser


    fun getAllUser() {

        val userList = mutableListOf<UserProfile>()


        viewModelScope.launch {

            userRepo.getAllUser().addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    userList.clear()

                    snapshot.children.forEach { dataSnapshot ->
                        val value = dataSnapshot.getValue(UserProfile::class.java)
                        value?.let {
                            userList.add(it)
                        }
                    }

                    _responseAllUser.postValue(userList)


                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }

            })


        }


    }


    private var _responseProfileUpdate = MutableLiveData<String>()
    val responseUpdateUserProfile: LiveData<String> = _responseProfileUpdate
    fun updateUser(userProfile: UserProfile, uri: Uri?) {


        if (uri == null) {
            updateUserInfo(userProfile)
        } else {

            viewModelScope.launch {
                val storageRef = storageRef.child("profile_picture_${System.currentTimeMillis()}.jpg")

                userRepo.uploadProfileImage(uri, storageRef).addOnSuccessListener {
                    storageRef.downloadUrl.addOnCompleteListener { taskResult ->

                        userProfile.image = taskResult.result.toString()

                        updateUserInfo(userProfile)

                    }.addOnFailureListener {
                        OnFailureListener { e ->
                            Log.d("TAG", "Error : ${e.localizedMessage}")
                        }
                    }
                }

            }


        }


    }

    private fun updateUserInfo(userProfile: UserProfile) {
        viewModelScope.launch {
            userRepo.updateUser(userProfile).addOnCompleteListener {
                _responseProfileUpdate.postValue(SuccessMessage)

            }.addOnFailureListener {

                _responseProfileUpdate.postValue(it.localizedMessage)
            }

        }


    }


}