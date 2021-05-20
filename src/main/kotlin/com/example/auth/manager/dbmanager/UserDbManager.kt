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
        val session =  sessionFactory.openSession()
        val user = session.get(User::class.java, id)
        session.close()
        return user
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

        val user =  session
            .createQuery(query)
            .uniqueResult()
        session.close()
        return user

    }

    @Transactional
    open fun save(user: User): User? {
        val session = sessionFactory.openSession()
        session.saveOrUpdate(user)
        session.close()
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

        val users =  session
            .createQuery(query)
            .list()
        session.close()
        return users
    }

}