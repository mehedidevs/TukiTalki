package com.mehedi.tukitalki.ui.auth.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mehedi.tukitalki.base.BaseFragment
import com.mehedi.tukitalki.data.RequestUserRegister
import com.mehedi.tukitalki.databinding.FragmentRegisterBinding
import com.mehedi.tukitalki.utils.registrationErrorMessage
import com.mehedi.tukitalki.utils.registrationSuccessMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.responseRegistration.observe(viewLifecycleOwner) {
            when (it) {
                registrationSuccessMessage -> {
                    Toast.makeText(requireContext(), registrationSuccessMessage, Toast.LENGTH_LONG)
                        .show()

                }

                registrationErrorMessage -> {
                    Toast.makeText(requireContext(), registrationErrorMessage, Toast.LENGTH_LONG)
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
                val request = RequestUserRegister(name = name, email = email, password = password)
                viewModel.registration(request)

            }


        }





        super.onViewCreated(view, savedInstanceState)
    }


}