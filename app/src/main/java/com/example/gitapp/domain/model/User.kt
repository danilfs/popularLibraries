package com.example.gitapp.domain.model

data class User(
    val id: Int,
    val login: String,
) {
    val avatarUrl get() = "https://avatars.githubusercontent.com/u/$id?v=4"
    val githubUrl get() = "https://github.com/$login"
}
