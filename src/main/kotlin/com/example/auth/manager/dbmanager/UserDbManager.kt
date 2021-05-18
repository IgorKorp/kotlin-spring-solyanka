package com.example.auth.manager.dbmanager

import com.example.auth.entities.User
import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Transactional



open class UserDbManager(
    private val sessionFactory: SessionFactory,

) {
    @Transactional
    open fun getUser(login: String): User? {
        return sessionFactory.currentSession.get(User::class.java, login)
    }



}