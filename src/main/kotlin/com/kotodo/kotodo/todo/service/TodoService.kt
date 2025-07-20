package com.kotodo.kotodo.todo.service

import com.kotodo.kotodo.global.dto.PaginationRequest
import com.kotodo.kotodo.todo.dto.TodoRequest
import com.kotodo.kotodo.todo.model.Todo
import com.kotodo.kotodo.todo.repository.TodoJpaRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoJpaRepository: TodoJpaRepository
) {
    fun search(request: TodoRequest, paginationRequest: PaginationRequest): List<Todo> {
        return todoJpaRepository.search(request, paginationRequest)
    }
}
