package com.example.chatapp.service

import com.example.chatapp.model.Message
import com.example.chatapp.repository.MessageRepository
import io.github.serpro69.kfaker.faker
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class MessageServiceImpl(
    val db: MessageRepository
) : MessageService {
    override fun findFakeMessages(): List<Message> {
        val faker = faker {}
        return mutableListOf(
            Message(1L, faker.random.randomString(), Instant.now().toEpochMilli(), 1L),
            Message(2L, faker.random.randomString(), Instant.now().toEpochMilli(), 1L),
            Message(3L, faker.random.randomString(), Instant.now().toEpochMilli(), 1L),
        )
    }

    override fun findMessages(): List<Message> {
        return db.findMessages()
    }

    override fun post(message: Message): Message {
        return db.save(message)
    }
}