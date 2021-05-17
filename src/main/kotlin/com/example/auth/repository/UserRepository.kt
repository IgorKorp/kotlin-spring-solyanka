package com.example.auth.repository

import com.example.auth.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun existsByLogin(@Param("login") login: String): Boolean

    fun findByLogin(@Param("login") login: String): User?

}