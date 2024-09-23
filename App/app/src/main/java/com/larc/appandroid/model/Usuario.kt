package com.larc.appandroid.model

data class Usuario(
    val id: String,
    val email: String,
    val password: String,
    val name: String,
    val lastName: String,
    val googleId: Int,
    val isActive: Boolean,
    val role: List<String>
)
