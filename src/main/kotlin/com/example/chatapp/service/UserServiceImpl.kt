package com.example.chatapp.service

import com.example.chatapp.model.User
import com.example.chatapp.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.webjars.NotFoundException

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun findUsers(): List<User> {
        return userRepository.findUsers()
    }

    override fun findUser(id: Long): User {
        return userRepository.findByIdOrNull(id) ?: throw NotFoundException("User with id = $id not found")
    }

    override fun saveUser(user: User): User {
        return userRepository.save(user)
    }
}