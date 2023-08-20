package com.mehedi.tukitalki.ui.auth.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.tukitalki.data.RequestUserRegister
import com.mehedi.tukitalki.repositories.AuthRepo
import com.mehedi.tukitalki.utils.registrationErrorMessage
import com.mehedi.tukitalki.utils.registrationSuccessMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {

    private var _resposne = MutableLiveData<String>()
    val responseRegistration: LiveData<String> = _resposne


    fun registration(
        requestUserRegister: RequestUserRegister
    ) {


        viewModelScope.launch {
            repo.registration(requestUserRegister).addOnCompleteListener {

                if (it.isSuccessful) {
                    _resposne.postValue(registrationSuccessMessage)
                }
//                else {
//                   // _resposne.postValue(registrationErrorMessage)
//                   _resposne.postValue(it.exception?.localizedMessage ?: registrationErrorMessage)
//
//                }


            }.addOnFailureListener {
                Log.d("TAG", "${it.localizedMessage}: ")


                _resposne.postValue(it.localizedMessage ?: registrationErrorMessage)

            }

        }


    }


}