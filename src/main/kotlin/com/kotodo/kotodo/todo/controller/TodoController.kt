package com.kotodo.kotodo.todo.controller

import com.kotodo.kotodo.global.config.logger
import com.kotodo.kotodo.global.dto.PaginationRequest
import com.kotodo.kotodo.global.dto.ResponseData
import com.kotodo.kotodo.todo.dto.TodoRequest
import com.kotodo.kotodo.todo.dto.TodoResponse
import com.kotodo.kotodo.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController(
    private val todoService: TodoService
) {

    private val log = logger()

    @GetMapping
    fun readAll(
        todoRequest: TodoRequest,
        @Valid paginationRequest: PaginationRequest
    ): ResponseEntity<ResponseData<List<TodoResponse>>> {
        log.info("TodoController.readAll: Todo 목록 조회 요청 - request: {}, pagination: {}", todoRequest, paginationRequest)

        val todos = todoService.search(todoRequest, paginationRequest).map { TodoResponse.from(it) }
        return ResponseEntity.ok(ResponseData(httpStatus = HttpStatus.OK.value(), data = todos))
    }
}