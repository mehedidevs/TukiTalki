package com.mehedi.tukitalki.ui.auth.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mehedi.tukitalki.base.BaseFragment
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.databinding.FragmentRegisterBinding
import com.mehedi.tukitalki.utils.DummyImgLink
import com.mehedi.tukitalki.utils.ErrorMessage
import com.mehedi.tukitalki.utils.SuccessMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.responseRegistration.observe(viewLifecycleOwner) {
            when (it) {
                SuccessMessage -> {
                    Toast.makeText(requireContext(), SuccessMessage, Toast.LENGTH_LONG)
                        .show()

                }

                ErrorMessage -> {
                    Toast.makeText(requireContext(), ErrorMessage, Toast.LENGTH_LONG)
                        .show()
                }

                else -> {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG)
                        .show()
                }

            }


        }



        binding.registerButton.setOnClickListener {
            binding.apply {
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val confirmPassword = confirmPasswordEditText.text.toString()
                val request = RequestUserRegister(
                    name = name,
                    email = email,
                    password = password,
                    createdAt = System.currentTimeMillis(),
                    image = DummyImgLink
                )
                viewModel.registration(request)

            }


        }


        binding.loginTextView.setOnClickListener {

            findNavController().popBackStack()


        }





        super.onViewCreated(view, savedInstanceState)
    }


}