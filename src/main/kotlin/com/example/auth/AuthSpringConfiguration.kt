package com.example.auth

import com.example.auth.manager.UserManager
import com.example.auth.manager.dbmanager.UserDbManager
import com.example.auth.repository.UserRepository
import com.example.auth.repository.UserTokenRepository
import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthSpringConfiguration {


//    @Bean
//    fun userDbManager(sessionFactory: SessionFactory) = UserDbManager(sessionFactory)


    @Bean
    fun userManager(userRepository: UserRepository, userTokenRepository: UserTokenRepository, /*userDbManager: UserDbManager*/) = UserManager(userRepository = userRepository, userTokenRepository = userTokenRepository,/* userDbManager = userDbManager*/)


}