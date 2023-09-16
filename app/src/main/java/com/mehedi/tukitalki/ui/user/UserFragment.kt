package com.mehedi.tukitalki.ui.user


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseUser
import com.mehedi.tukitalki.base.BaseFragment
import com.mehedi.tukitalki.data.user.UserProfile
import com.mehedi.tukitalki.databinding.FragmentUserBinding
import com.mehedi.tukitalki.ui.chat.ChatActivity
import com.mehedi.tukitalki.ui.profile.OthersProfileActivity
import com.mehedi.tukitalki.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate),
    UserAdapter.Listener {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var adapter: UserAdapter

    @Inject
    lateinit var user: FirebaseUser
    var userList = mutableListOf<UserProfile>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter(this)

        viewModel.getAllUser()



        viewModel.responseAllUserProfile.observe(viewLifecycleOwner) {
            userList.clear()

            it.forEach { fUser ->
                if (fUser.userId != user.uid) {
                    userList.add(fUser)
                }
            }


            adapter.submitList(userList)
            binding.userRcv.adapter = adapter


        }


    }

    override fun profileClicked(userId: String) {

        val intent = Intent(requireContext(), OthersProfileActivity::class.java)
        intent.putExtra(OthersProfileActivity.USER_KEY, userId)
        startActivity(intent)


    }

    override fun messageMeClicked(userId: String) {
        val intent = Intent(requireContext(), ChatActivity::class.java)
        intent.putExtra(ChatActivity.REMOTE_USER_KEY, userId)
        startActivity(intent)

    }


}