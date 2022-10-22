package com.example.chatapp.repository

import com.example.chatapp.model.Message
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: CrudRepository<Message, Long> {
    @Query("select * from messages", nativeQuery = true)
    fun findMessages(): List<Message>
}