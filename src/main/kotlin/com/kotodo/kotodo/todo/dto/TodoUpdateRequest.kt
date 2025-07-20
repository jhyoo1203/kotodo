package com.kotodo.kotodo.todo.dto

import jakarta.validation.constraints.Size

data class TodoUpdateRequest(
    @Size(min = 1, max = 100)
    val title: String? = null,
    val description: String? = null,
    val completed: Boolean = false
)
