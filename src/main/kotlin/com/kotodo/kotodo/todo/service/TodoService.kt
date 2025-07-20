package com.kotodo.kotodo.todo.service

import com.kotodo.kotodo.global.dto.PaginationRequest
import com.kotodo.kotodo.todo.dto.TodoCreateRequest
import com.kotodo.kotodo.todo.dto.TodoSearchRequest
import com.kotodo.kotodo.todo.dto.TodoUpdateRequest
import com.kotodo.kotodo.todo.model.Todo
import com.kotodo.kotodo.todo.repository.TodoJpaRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoJpaRepository: TodoJpaRepository
) {
    fun search(request: TodoSearchRequest, paginationRequest: PaginationRequest): List<Todo> {
        return todoJpaRepository.search(request, paginationRequest)
    }

    fun create(request: TodoCreateRequest): Todo {
        val todo = Todo(
            title = request.title,
            description = request.description,
            completed = request.completed
        )
        return todoJpaRepository.save(todo)
    }

    fun update(id: Long, request: TodoUpdateRequest): Todo {
        val todo = todoJpaRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Todo ${id}가 존재하지 않습니다.") }

        todo.update(
            title = request.title ?: todo.title,
            description = request.description ?: todo.description,
            completed = request.completed
        )

        return todoJpaRepository.save(todo)
    }
}
