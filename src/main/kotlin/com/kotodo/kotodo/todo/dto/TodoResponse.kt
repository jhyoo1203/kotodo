package com.kotodo.kotodo.todo.dto

import com.kotodo.kotodo.todo.model.Todo

data class TodoResponse(
    val id: Long,
    val title: String,
    val description: String? = null,
    val completed: Boolean
) {
    companion object {
        fun from(todo: Todo): TodoResponse {
            return TodoResponse(
                id = todo.id,
                title = todo.title,
                description = todo.description,
                completed = todo.completed
            )
        }
    }
}

