package com.mehedi.tukitalki.ui.profile

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.tukitalki.R
import com.mehedi.tukitalki.databinding.ActivityOthersProfileBinding
import com.mehedi.tukitalki.ui.chat.ChatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OthersProfileActivity : AppCompatActivity() {

    companion object {
        const val USER_KEY = "user_key_id"


    }

    var remoteUSerID: String? = null

    private lateinit var binding: ActivityOthersProfileBinding

    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOthersProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra(USER_KEY)?.let {
            viewModel.getUserById(it)
            remoteUSerID = it

        }

        binding.chatBtn.setOnClickListener { _ ->
            remoteUSerID?.let {
                val intent = Intent(this@OthersProfileActivity, ChatActivity::class.java)
                intent.putExtra(ChatActivity.REMOTE_USER_KEY, it)
                startActivity(intent)
            }
        }






        viewModel.responseUserProfile.observe(this) {

            binding.apply {
                userName.text = it.name
                userEmail.text = it.email
                userAbout.text = it.about
                profileImage.load(it.image)
                coverImage.load(it.image)


            }


        }


    }
}