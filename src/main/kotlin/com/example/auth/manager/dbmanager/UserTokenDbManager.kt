package com.example.auth.manager.dbmanager

import com.example.auth.entities.User
import com.example.auth.entities.UserToken
import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Transactional
import javax.persistence.criteria.Predicate

open class UserTokenDbManager(
    private val sessionFactory: SessionFactory,
) {
    @Transactional
    open fun get(id: Long?): UserToken? {
        return sessionFactory.openSession().get(UserToken::class.java, id)
    }
    @Transactional
    open fun getUserTokenByLogin(login: String): UserToken? {
        val session = sessionFactory.openSession()
        val criteriaBuilder = session.criteriaBuilder
        val query = criteriaBuilder.createQuery(UserToken::class.java)
        val table = query.from(UserToken::class.java)
        val loginField = table.get<UserToken>(UserToken::login.name)
        val conditions = mutableListOf<Predicate>()
        conditions.add(criteriaBuilder.equal(loginField, login))

        query
            .select(table)
            .where(*conditions.toTypedArray())

        return session
            .createQuery(query)
            .uniqueResult()
    }

    @Transactional
    open fun save(userToken: UserToken): UserToken? {
        sessionFactory.openSession().save(userToken)
        return userToken
    }

    @Transactional
    open fun update(userToken: UserToken?): UserToken? {
        sessionFactory.openSession().update(userToken)
        return userToken
    }
}