package com.mehedi.tukitalki.ui.auth.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.tukitalki.data.register.RequestUserRegister
import com.mehedi.tukitalki.repositories.AuthRepo
import com.mehedi.tukitalki.repositories.UserRepo
import com.mehedi.tukitalki.utils.ErrorMessage
import com.mehedi.tukitalki.utils.SuccessMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: AuthRepo,
    private val userRepo: UserRepo
) : ViewModel() {

    private var _resposne = MutableLiveData<String>()
    val responseRegistration: LiveData<String> = _resposne


    fun registration(
        requestUserRegister: RequestUserRegister
    ) {


        viewModelScope.launch {
            repo.registration(requestUserRegister).addOnCompleteListener {

                if (it.isSuccessful) {
                    requestUserRegister.userId= it.result.user!!.uid

                    viewModelScope.launch {
                        userRepo.createUser(requestUserRegister).addOnCompleteListener { dbIt ->

                            if (dbIt.isSuccessful) {
                                _resposne.postValue(SuccessMessage)
                            } else {
                                _resposne.postValue(ErrorMessage)
                                _resposne.postValue(
                                    it.exception?.localizedMessage ?: ErrorMessage
                                )
                            }

                        }


                    }


                }

            }.addOnFailureListener {
                Log.d("TAG", "${it.localizedMessage}: ")


                _resposne.postValue(it.localizedMessage ?: ErrorMessage)

            }

        }


    }


}