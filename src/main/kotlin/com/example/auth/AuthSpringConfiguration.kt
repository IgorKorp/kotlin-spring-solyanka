package com.example.auth

import com.example.auth.manager.dbmanager.UserManager
import com.example.auth.repository.UserRepository
import com.example.auth.repository.UserTokenRepository
import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AuthSpringConfiguration {

    @Bean
    fun userManager(userRepository: UserRepository, userTokenRepository: UserTokenRepository) = UserManager(userRepository = userRepository, userTokenRepository = userTokenRepository)

}