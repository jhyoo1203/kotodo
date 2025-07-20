package com.kotodo.kotodo.todo.controller

import com.kotodo.kotodo.global.config.logger
import com.kotodo.kotodo.global.dto.PaginationRequest
import com.kotodo.kotodo.global.dto.ResponseData
import com.kotodo.kotodo.todo.dto.TodoCreateRequest
import com.kotodo.kotodo.todo.dto.TodoSearchRequest
import com.kotodo.kotodo.todo.dto.TodoResponse
import com.kotodo.kotodo.todo.dto.TodoUpdateRequest
import com.kotodo.kotodo.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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
        todoSearchRequest: TodoSearchRequest,
        @Valid paginationRequest: PaginationRequest
    ): ResponseEntity<ResponseData<List<TodoResponse>>> {
        log.info("TodoController.readAll: Todo 목록 조회 요청 - request: {}, pagination: {}", todoSearchRequest, paginationRequest)

        val todos = todoService.search(todoSearchRequest, paginationRequest).map { TodoResponse.from(it) }
        return ResponseEntity.ok(ResponseData(httpStatus = HttpStatus.OK.value(), data = todos))
    }

    @PostMapping
    fun create(
        @Valid @RequestBody todoCreateRequest: TodoCreateRequest
    ): ResponseEntity<ResponseData<TodoResponse>> {
        log.info("TodoController.create: Todo 생성 요청 - request: {}", todoCreateRequest)

        val createdTodo = todoService.create(todoCreateRequest)
        return ResponseEntity.ok(ResponseData(httpStatus = HttpStatus.CREATED.value(), data = TodoResponse.from(createdTodo)))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody todoUpdateRequest: TodoUpdateRequest
    ): ResponseEntity<ResponseData<TodoResponse>> {
        log.info("TodoController.update: Todo 수정 요청 - id: {}, request: {}", id, todoUpdateRequest)

        val updatedTodo = todoService.update(id, todoUpdateRequest)
        return ResponseEntity.ok(ResponseData(httpStatus = HttpStatus.OK.value(), data = TodoResponse.from(updatedTodo)))
    }
}