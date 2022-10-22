package com.example.chatapp.controller

import com.example.chatapp.model.Message
import com.example.chatapp.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageResource(
    private val messageService: MessageService
) {

    @GetMapping("/api/v1/fakeMessages")
    fun fakeMessages(): List<Message> = messageService.findFakeMessages()

    @GetMapping("/api/v1/messages")
    fun messages(): List<Message> = messageService.findMessages()

    @PostMapping("/api/v1/messages")
    fun post(@RequestBody message: Message) {
        messageService.post(message)
    }
}