package com.example.auth.security

import java.util.concurrent.ThreadLocalRandom

const val AUTH_TOKEN_LENGTH: Int = 12

data class AuthToken (
    var token: String = "",
    var profileId: Long = 0
) {
    companion object {
        fun generateToken(size: Int): String {
            val token = StringBuilder()
            val chars = "0123456789qwertyuiopasdfghjklxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM"
            for (i in 0..size - 1) {
                token.append(chars[ThreadLocalRandom.current().nextInt(chars.length)])
            }
            return token.toString()
        }
    }
}