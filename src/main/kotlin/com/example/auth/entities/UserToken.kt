package com.example.auth.entities

import javax.persistence.*

@Entity
@Table(name = "users_token")
class UserToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,

    @Column(unique =  true, nullable = true)
    var token: String? = null,

    @Column(unique =  true, nullable = false)
    var login: String? = null
)

