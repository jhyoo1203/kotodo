package com.kotodo.kotodo.todo.repository

import com.kotodo.kotodo.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoJpaRepository : JpaRepository<Todo, Long>, TodoRepository