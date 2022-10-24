package com.example.chatapp.websocketcontroller

import com.example.chatapp.model.Message
import com.example.chatapp.model.User
import com.example.chatapp.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WsUserController(
    private val userService: UserService
) {
    @MessageMapping("/user")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun post(user: User): Message? {
        logger.info("Saving user ${user.name}")
        return Message(userService.saveUser(user).name)
    }

    @MessageMapping("/users")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun findUsers(): List<User> {
        logger.info("Show all users")
        return userService.findUsers()
    }

    @MessageMapping("/user/{id}")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun findUser(@DestinationVariable id: Long): User? {
        logger.info("Get user by id = $id")
        return userService.findUser(id)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.declaringClass)
    }
}