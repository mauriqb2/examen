package com.example.examen

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel(val userRepository: UserRepository) : ViewModel() {
    val model: LiveData<UiModel>
        get() = _model
    private val _model = MutableLiveData<UiModel>()

    sealed class UiModel {
        class SavingUser(val success: Boolean): UiModel()
        object Loading: UiModel()
    }

    fun doSaveUser(user: UserModel) {
        _model.value = UiModel.Loading
        val runnable = Runnable {
            _model.value = UiModel.SavingUser( userRepository.saveUser(user))
        }
        Handler().postDelayed(runnable, 3000)

    }
}