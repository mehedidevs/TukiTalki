package com.mehedi.tukitalki.ui.chat

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mehedi.tukitalki.R
import com.mehedi.tukitalki.databinding.ActivityChatBinding
import com.mehedi.tukitalki.ui.profile.OthersProfileActivity

class ChatActivity : AppCompatActivity() {


    companion object {
        const val REMOTE_USER_KEY = "remote_user_key_id"


    }

    private lateinit var binding: ActivityChatBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.getStringExtra(REMOTE_USER_KEY)?.let {
            Log.d("TAG", "User Id From ${ChatActivity.javaClass.name}: $it ")
        }



    }
}