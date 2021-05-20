package com.example.auth.manager.dbmanager

import com.example.auth.entities.User
import com.google.gson.Gson
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable
import javax.persistence.criteria.Predicate


open class UserDbManager(
    private val sessionFactory: SessionFactory,

) {
    @Transactional
    open fun getUser(id: Long): User? {
        return sessionFactory.openSession().get(User::class.java, id)
    }

    @Transactional
    open fun getUserByLogin(login: String): User? {
        val session = sessionFactory.openSession()
        val criteriaBuilder = session.criteriaBuilder
        val query = criteriaBuilder.createQuery(User::class.java)
        val table = query.from(User::class.java)
        val loginField = table.get<User>(User::login.name)
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
    open fun save(user: User): User? {
        sessionFactory.openSession().saveOrUpdate(user)
        return user
    }

    @Transactional
    open fun getUsers(): List<User> {
        val session = sessionFactory.openSession()
        val criteriaBuilder = session.criteriaBuilder
        val query = criteriaBuilder.createQuery(User::class.java)
        val table = query.from(User::class.java)

        query
            .select(table)

        return session
            .createQuery(query)
            .list()
    }

}