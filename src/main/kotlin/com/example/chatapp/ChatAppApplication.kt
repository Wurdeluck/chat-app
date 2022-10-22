package com.example.chatapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan
class ChatAppApplication

fun main(args: Array<String>) {
    runApplication<ChatAppApplication>(*args)
}
