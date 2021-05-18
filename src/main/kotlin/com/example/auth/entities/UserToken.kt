package com.example.auth.entities

import javax.persistence.*

@Entity
@Table(name = "users_token")
class UserToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique =  true)
    var login: String = "",

    @Column(unique =  true)
    var token: String = ""

)

