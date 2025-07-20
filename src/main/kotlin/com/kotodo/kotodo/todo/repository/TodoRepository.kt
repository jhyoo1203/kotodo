package com.kotodo.kotodo.todo.repository

import com.kotodo.kotodo.global.dto.PaginationRequest
import com.kotodo.kotodo.todo.dto.TodoSearchRequest
import com.kotodo.kotodo.todo.model.Todo

interface TodoRepository {
    fun search(request: TodoSearchRequest, paginationRequest: PaginationRequest): List<Todo>
}
