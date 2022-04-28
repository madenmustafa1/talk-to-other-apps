package com.maden.baseapp

class GetUser {
    fun getUser() = User(
        name = "Mustafa",
        surname = "Maden",
        email = "mustafa@maden.com",
        age = 1
    )
}

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val age: Int
)