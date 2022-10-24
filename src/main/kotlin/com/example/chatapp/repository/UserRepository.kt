package com.example.chatapp.repository

import com.example.chatapp.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    @Query("select * from users", nativeQuery = true)
    fun findUsers(): List<User>
}