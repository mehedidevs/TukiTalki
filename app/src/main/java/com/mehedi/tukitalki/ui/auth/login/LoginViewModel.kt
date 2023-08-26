package com.mehedi.tukitalki.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.tukitalki.data.login.RequestUserLogin
import com.mehedi.tukitalki.repositories.AuthRepo
import com.mehedi.tukitalki.utils.ErrorMessage
import com.mehedi.tukitalki.utils.SuccessMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {

    private var _resposne = MutableLiveData<String>()
    val responseLogin: LiveData<String> = _resposne


    fun login(
        request: RequestUserLogin
    ) {


        viewModelScope.launch {
            repo.login(request).addOnCompleteListener {

                if (it.isSuccessful) {
                    _resposne.postValue(SuccessMessage)
                }
//                else {
//                   // _resposne.postValue(registrationErrorMessage)
//                   _resposne.postValue(it.exception?.localizedMessage ?: registrationErrorMessage)
//
//                }


            }.addOnFailureListener {
                Log.d("TAG", "${it.localizedMessage}: ")


                _resposne.postValue(it.localizedMessage ?: ErrorMessage)

            }

        }


    }


}