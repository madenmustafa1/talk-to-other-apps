package com.maden.loginapp

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val age: Int
)

fun returnUserModelString(user: User?): String {
    return "Name: ${user?.name}\n" +
            "Surname ${user?.surname}\n" +
            "Age: ${user?.age}\n" +
            "Mail: ${user?.email}"
}