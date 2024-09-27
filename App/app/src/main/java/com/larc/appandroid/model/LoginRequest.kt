package com.larc.appandroid.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class SignupRequest(
    val email: String,
    val name: String,
    val lastName: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val name: String,
    val lastName: String,
    val password: String,
    val token: String
)
