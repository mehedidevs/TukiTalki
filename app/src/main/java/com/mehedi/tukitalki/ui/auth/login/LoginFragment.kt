package com.mehedi.tukitalki.ui.auth.login


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mehedi.tukitalki.R
import com.mehedi.tukitalki.base.BaseFragment
import com.mehedi.tukitalki.data.login.RequestUserLogin
import com.mehedi.tukitalki.databinding.FragmentLoginBinding
import com.mehedi.tukitalki.utils.ErrorMessage
import com.mehedi.tukitalki.utils.SuccessMessage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.responseLogin.observe(viewLifecycleOwner) {
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


        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            login(email, password)


        }


        binding.registerTextView.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)


        }


    }

    private fun login(email: String, password: String) {
        val request = RequestUserLogin(email = email, password = password)


        viewModel.login(request)


    }


}