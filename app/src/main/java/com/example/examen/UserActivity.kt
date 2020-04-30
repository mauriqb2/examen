package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class UserActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = UserViewModel(UserRepository())
        userViewModel.model.observe(this, Observer(::updateUi))

        button.setOnClickListener {
            val user = UserModel(editText.text.toString(),editText2.text.toString(),editText3.text.toString())
            userViewModel.doSaveUser(user)
        }
    }

    private fun updateUi(model: UserViewModel.UiModel?) {
        loading_progress_bar.visibility = if ( model is UserViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when ( model ) {
            is UserViewModel.UiModel.SavingUser -> validarSavingUser(model.success)
        }
    }

    private fun validarSavingUser( resp: Boolean) {
        if( resp) {
            Toast.makeText(this, "Se registro el usuario de manera correcta", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "No se registro el usuario", Toast.LENGTH_LONG).show()
        }

    }
}
