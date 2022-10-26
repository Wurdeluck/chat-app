package com.example.chatapp.websocketcontroller

import com.example.chatapp.model.Message
import com.example.chatapp.model.MessageDto
import com.example.chatapp.service.MessageService
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.time.Instant

@Controller
class WebsocketMessageController(
    private val messageService: MessageService
) {

    // @MessageMapping("/hello")
    // @SendTo("/topic/messages")
    // @Throws(Exception::class)
    // fun greeting(name: String): Message? {
    //     Thread.sleep(1000) // simulated delay
    //     return Message(1L, "Hello, $name")
    // }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun post(messageDto: MessageDto): Message? {
        logger.info("Saving message ${messageDto.text}")
        val currentTime = Instant.now().toEpochMilli()
        return messageService.post(Message(messageDto.text, currentTime, messageDto.userId))
    }

    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun messages(): List<Message> {
        logger.debug("Trying to get all messages")
        return messageService.findMessages()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.declaringClass)
    }
}