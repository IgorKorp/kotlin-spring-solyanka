package com.example.auth

import com.example.auth.manager.UserManager
import com.example.auth.manager.dbmanager.UserDbManager
import com.example.auth.manager.dbmanager.UserTokenDbManager
import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthSpringConfiguration {

    @Bean
    fun userDbManager(sessionFactory: SessionFactory) = UserDbManager(sessionFactory)

    @Bean
    fun userTokenDbManager(sessionFactory: SessionFactory) = UserTokenDbManager(sessionFactory)

    @Bean
    fun userManager(
        userDbManager: UserDbManager,
        userTokenDbManager: UserTokenDbManager
    ) = UserManager(
        userDbManager = userDbManager,
        userTokenDbManager = userTokenDbManager
    )

}