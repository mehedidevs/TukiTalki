package com.mehedi.tukitalki.ui.user


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mehedi.tukitalki.base.BaseFragment
import com.mehedi.tukitalki.databinding.FragmentUserBinding
import com.mehedi.tukitalki.ui.chat.ChatActivity
import com.mehedi.tukitalki.ui.profile.OthersProfileActivity
import com.mehedi.tukitalki.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate),
    UserAdapter.Listener {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var adapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter(this)

        viewModel.getAllUser()

        viewModel.responseAllUserProfile.observe(viewLifecycleOwner) {

            adapter.submitList(it)
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