package com.kotodo.kotodo.todo.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todos")
class Todo(
    @Column(nullable = false)
    var title: String,

    @Column
    var description: String? = null,

    @Column(nullable = false)
    var completed: Boolean = false
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        protected set

    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        private set

    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
        private set

    // JPA용 기본 생성자
    protected constructor() : this("")

    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        this.createdAt = now
        this.updatedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        this.updatedAt = LocalDateTime.now()
    }

    fun update(
        title: String? = null,
        description: String? = null,
        completed: Boolean? = null
    ) {
        title?.let { this.title = it }
        description?.let { this.description = it }
        completed?.let { this.completed = it }
    }
}
