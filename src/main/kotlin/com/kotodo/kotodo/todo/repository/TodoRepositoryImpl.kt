package com.kotodo.kotodo.todo.repository

import com.kotodo.kotodo.global.dto.PaginationRequest
import com.kotodo.kotodo.todo.dto.TodoRequest
import com.kotodo.kotodo.todo.model.QTodo
import com.kotodo.kotodo.todo.model.Todo
import com.querydsl.jpa.impl.JPAQueryFactory

class TodoRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : TodoRepository {

    override fun search(request: TodoRequest, paginationRequest: PaginationRequest): List<Todo> {
        val qTodo = QTodo.todo

        return jpaQueryFactory
            .selectFrom(qTodo)
            .where(
                request.title?.let { qTodo.title.containsIgnoreCase(it) },
                request.description?.let { qTodo.description.containsIgnoreCase(it) },
                qTodo.completed.eq(request.completed)
            )
            .offset((paginationRequest.page * paginationRequest.size).toLong())
            .limit(paginationRequest.size.toLong())
            .fetch()
    }
}
