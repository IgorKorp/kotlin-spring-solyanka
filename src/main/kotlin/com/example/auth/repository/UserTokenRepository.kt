package com.example.auth.repository

import com.example.auth.entities.UserToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface UserTokenRepository: JpaRepository<UserToken, Long> {
}