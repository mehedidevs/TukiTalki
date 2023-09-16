package com.mehedi.tukitalki.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.mehedi.tukitalki.data.chat.Chat
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.user.UserProfile
import com.mehedi.tukitalki.repositories.ChatRepo
import com.mehedi.tukitalki.repositories.UserRepo
import com.mehedi.tukitalki.utils.ErrorMessage
import com.mehedi.tukitalki.utils.SuccessMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val chatRepo: ChatRepo,
) : ViewModel() {

    private var _resposne = MutableLiveData<List<Chat>>()
    val responseChat: LiveData<List<Chat>> = _resposne


    fun getChat(myId: String, remoteUserID: String) {

        val chats = mutableListOf<Chat>()
        viewModelScope.launch {
            chatRepo.getChats().addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    chats.clear()
                    snapshot.children.forEach { dataSnapshot ->
                        val value = dataSnapshot.getValue(Chat::class.java)
                        value?.let {
                            if (it.receiverID == myId && it.senderID == remoteUserID || it.receiverID == remoteUserID && it.senderID == myId) {
                                chats.add(it)
                            }
                        }
                    }

                    _resposne.postValue(chats)


                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }

            })


        }


    }


    private var _responseSend = MutableLiveData<String>()
    val responseMessageSend: LiveData<String> = _responseSend

    fun sendMessage(
        chat: Chat
    ) {


        viewModelScope.launch {


            chatRepo.sendChat(chat).addOnCompleteListener {

                if (it.isSuccessful) {

                    _responseSend.postValue(SuccessMessage)
                }


            }.addOnFailureListener {
                Log.d("TAG", "${it.localizedMessage}: ")
                _responseSend.postValue(it.localizedMessage)


            }

        }


    }


//    fun getUserById(userId: String) {
//
//
//        viewModelScope.launch {
//
//            userRepo.getUserById(userId).addValueEventListener(object : ValueEventListener {
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//
//                    val value = snapshot.getValue(UserProfile::class.java)
//
//                    value?.let {
//
//                        _resposne.postValue(it)
//                    }
//
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Log.w("TAG", "Failed to read value.", error.toException())
//                }
//
//            })
//
//
//        }
//
//
//    }


//    private var _responseAllUser = MutableLiveData<List<UserProfile>>()
//    val responseAllUserProfile: LiveData<List<UserProfile>> = _responseAllUser
//
//
//    fun getAllUser() {
//
//        val userList = mutableListOf<UserProfile>()
//
//
//        viewModelScope.launch {
//
//            userRepo.getAllUser().addValueEventListener(object : ValueEventListener {
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//
//                    userList.clear()
//
//                    snapshot.children.forEach { dataSnapshot ->
//                        val value = dataSnapshot.getValue(UserProfile::class.java)
//                        value?.let {
//                            userList.add(it)
//                        }
//                    }
//
//                    _responseAllUser.postValue(userList)
//
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Log.w("TAG", "Failed to read value.", error.toException())
//                }
//
//            })
//
//
//        }
//
//
//    }


}