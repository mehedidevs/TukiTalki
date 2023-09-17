package com.mehedi.tukitalki.ui.profile


import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mehedi.tukitalki.R
import com.mehedi.tukitalki.base.BaseFragment
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.data.user.UserProfile
import com.mehedi.tukitalki.databinding.FragmentProfileBinding
import com.mehedi.tukitalki.utils.DummyImgLink
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {


    private val viewModel: ProfileViewModel by viewModels()

    lateinit var fileUri: Uri

    lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = FirebaseAuth.getInstance().currentUser!!
        user.let {
            viewModel.getUserById(it.uid)
        }


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        binding.logoutBtn.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.loginFragment)
        }

        binding.profileImage.setOnClickListener {

            ImagePicker.with(this)
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )  //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }


        }


        binding.updateBtn.setOnClickListener {
            val name = binding.userName.text.toString()
            val email = binding.userEmail.text.toString()
            val about = binding.userAbout.text.toString()

            val request = UserProfile(
                name = name,
                email = email,
                about = about,
                image = DummyImgLink,
                updatedAt = System.currentTimeMillis(),
                userId = user.uid
            )

            viewModel.updateUser(request, fileUri)


        }


        viewModel.responseUpdateUserProfile.observe(viewLifecycleOwner) {

            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()


        }



        viewModel.responseUserProfile.observe(viewLifecycleOwner) {

            binding.apply {
                userName.setText(it.name)
                userEmail.setText(it.email)
                userAbout.setText(it.about)
                profileImage.load(it.image)
                coverImage.load(it.image)


            }


        }


    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {

                    fileUri = data?.data!!

                    binding.profileImage.load(fileUri)

                }

                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }


}