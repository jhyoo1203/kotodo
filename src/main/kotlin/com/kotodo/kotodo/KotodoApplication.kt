package com.kotodo.kotodo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotodoApplication

fun main(args: Array<String>) {
    runApplication<KotodoApplication>(*args)
}
