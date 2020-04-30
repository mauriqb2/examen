package com.example.examen

class UserRepository {
    fun saveUser(user: UserModel) : Boolean = (user.Lastname != "" && user.Name != "" && user.email != "")
}