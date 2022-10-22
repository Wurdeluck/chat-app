package com.example.chatapp.service

import com.example.chatapp.model.Message

interface MessageService {
    fun findMessages(): List<Message>

    fun findFakeMessages(): List<Message>

    fun post(message: Message): Message
}