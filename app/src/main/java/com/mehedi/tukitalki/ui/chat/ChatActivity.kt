package com.mehedi.tukitalki.ui.chat

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseUser
import com.mehedi.tukitalki.R
import com.mehedi.tukitalki.data.chat.Chat
import com.mehedi.tukitalki.databinding.ActivityChatBinding
import com.mehedi.tukitalki.ui.profile.OthersProfileActivity
import com.mehedi.tukitalki.utils.SuccessMessage
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    val viewModel: ChatViewModel by viewModels()

    lateinit var adapter: ChatAdapter


    @Inject
    lateinit var user: FirebaseUser

    private var remoteUserID: String = ""


    companion object {
        const val REMOTE_USER_KEY = "remote_user_key_id"
    }

    private lateinit var binding: ActivityChatBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.getStringExtra(REMOTE_USER_KEY)?.let {
            remoteUserID = it
            viewModel.getChat(myId = user.uid, remoteUserID = remoteUserID)
        }

        binding.sendBtn.setOnClickListener {
            val message = binding.textInputEdt.text.toString().trim()
            val chatID = UUID.randomUUID().toString()

            val chat = Chat(
                senderID = user.uid,
                receiverID = remoteUserID,
                message = message,
                chatID = chatID,
                System.currentTimeMillis()
            )

            viewModel.sendMessage(chat)

            binding.textInputEdt.text?.clear()


        }

        viewModel.responseMessageSend.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

        }
        viewModel.responseChat.observe(this) { list ->
            val sortedChat = list.sortedBy { it.timeStamp }


            adapter = ChatAdapter(sortedChat, myID = user.uid)

            val lManger = LinearLayoutManager(this)
            lManger.stackFromEnd = true
            binding.chatRCV.layoutManager = lManger
            binding.chatRCV.adapter = adapter


        }


    }


}