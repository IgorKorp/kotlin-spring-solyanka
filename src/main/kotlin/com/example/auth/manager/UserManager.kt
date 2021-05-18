package com.example.auth.manager

import com.example.auth.dto.UserChangePasswordDto
import com.example.auth.dto.UserDto
import com.example.auth.entities.User
import com.example.auth.entities.UserToken
import com.example.auth.manager.dbmanager.UserDbManager
import com.example.auth.message.ResponseMessage
import com.example.auth.repository.UserRepository
import com.example.auth.repository.UserTokenRepository
import com.example.auth.security.AuthToken

class UserManager(
    private val userRepository: UserRepository,
    private val userTokenRepository: UserTokenRepository,
//    private val userDbManager: UserDbManager
) {

    fun all(): List<User> = userRepository.findAll()

//    fun test(): User? = userDbManager.getUser("Peck")

    fun createUser(userDto: UserDto): User {
        val user = User()
        user.login = userDto.login.trim()
        user.password = userDto.password.trim()
        return userRepository.save(user)
    }

    fun changePassword(login : String, userChangePasswordDto: UserChangePasswordDto): ResponseMessage {
        val user = userRepository.findByLogin(login)
        val userToken = userTokenRepository.findByLogin(login)
        if (user == null) {
            return ResponseMessage("login is not found ", user, 404)
        }

        if (userToken != null && userToken.token == userChangePasswordDto.token.trim()) {
            if (user.password.equals(userChangePasswordDto.oldPassword) && userChangePasswordDto.newPassword.trim() == userChangePasswordDto.newConfirmedPassword) {
                user.password = userChangePasswordDto.newPassword.trim()
                userRepository.save(user)
                return ResponseMessage("ok", user, 200)
            }
            return ResponseMessage("old password or new password is not valid", user, 400)
        }

        return ResponseMessage("token is not valid", user, 401)

    }

    fun authUser(userDto: UserDto): ResponseMessage {
        val user =  userRepository.findByLogin(userDto.login)
        if (user == null) {
            return ResponseMessage("user not found", user, 404)
        }
        if (user.password?.trim().equals(userDto.password.trim())) {
            val userToken = userTokenRepository.findByLogin(userDto.login)

            if (userToken != null) {
                return ResponseMessage("ok", userToken, 200)
            }
            val newUserToken = UserToken()
            newUserToken.login = userDto.login
            newUserToken.token = AuthToken.generateToken(80)
            userTokenRepository.save(newUserToken)
            return ResponseMessage("ok", newUserToken, 200)
        }

        return ResponseMessage("Unauthorized", user,401)

    }




}