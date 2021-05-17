package com.example.auth.entities

import javax.persistence.*
import javax.validation.constraints.*


@Entity
@Table(name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique =  true)
    var login: String? = null,


){
    var password: String? = null
        get() = field
        set(value) {
            field = value
        }
    override fun toString(): String{
        return "{id: ${this.id}, name: ${this.login}}";
    }
}