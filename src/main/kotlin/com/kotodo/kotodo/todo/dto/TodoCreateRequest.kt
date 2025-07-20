package com.kotodo.kotodo.todo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class TodoCreateRequest(
    @NotBlank
    @Size(min = 1, max = 100)
    val title: String,
    val description: String? = null,
    val completed: Boolean = false
)
