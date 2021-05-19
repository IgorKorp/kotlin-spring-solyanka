package com.example.auth.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(unique =  true, nullable = false)
    var login: String? = null,
){
    @JsonIgnore
    @Column(nullable = false)
    var password: String? = null
        get() = field
        set(value) {
            field = value
        }
    override fun toString(): String{
        return "{id: ${this.id}, name: ${this.login}}";
    }
}