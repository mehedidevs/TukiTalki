package com.mehedi.tukitalki.services.chat

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mehedi.tukitalki.data.chat.Chat
import com.mehedi.tukitalki.utils.CHATS
import javax.inject.Inject

class ChatServiceImpl @Inject constructor(private val dbRef: FirebaseDatabase) : ChatService {


    override suspend fun sendChat(request: Chat): Task<Void> {
        return dbRef.reference.child(CHATS).child(request.chatID).setValue(request)
    }

    override suspend fun getChat(): DatabaseReference {
        return dbRef.reference.child(CHATS)
    }


}