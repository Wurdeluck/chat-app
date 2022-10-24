package com.example.chatapp.service

import com.example.chatapp.model.User

interface UserService {

    fun findUsers(): List<User>

    fun findUser(id: Long): User

    fun saveUser(user: User): User

}