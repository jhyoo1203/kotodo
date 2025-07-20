package com.kotodo.kotodo.todo.dto

data class TodoSearchRequest(
    val title: String? = null,
    val description: String? = null,
    val completed: Boolean = false
)
