package com.example.auth.manager

import com.example.auth.dto.UserChangePasswordDto
import com.example.auth.dto.UserDto
import com.example.auth.entities.User
import com.example.auth.entities.UserToken
import com.example.auth.manager.dbmanager.UserDbManager
import com.example.auth.manager.dbmanager.UserTokenDbManager
import com.example.auth.message.ResponseMessage
import com.example.auth.security.AuthToken

class UserManager(
    private val userDbManager: UserDbManager,
    private val userTokenDbManager: UserTokenDbManager
) {

    fun all(): List<User> = userDbManager.getUsers()

    fun createUser(userDto: UserDto): User {
        val user = User()
        user.login = userDto.login.trim()
        user.password = userDto.password.trim()
        userDbManager.save(user)
        return user
    }

    fun changePassword(login : String, userChangePasswordDto: UserChangePasswordDto): ResponseMessage {
        val user = userDbManager.getUserByLogin(login)
        val userToken = userTokenDbManager.getUserTokenByLogin(login)
        if (user == null) {
            return ResponseMessage("login is not found ", user, 404)
        }

        if (userToken != null && userToken.token == userChangePasswordDto.token.trim()) {
            if (user.password.equals(userChangePasswordDto.oldPassword) && userChangePasswordDto.newPassword.trim() == userChangePasswordDto.newConfirmedPassword) {
                user.password = userChangePasswordDto.newPassword.trim()
                userDbManager.save(user)
                return ResponseMessage("ok", user, 200)
            }
            return ResponseMessage("old password or new password is not valid", user, 400)
        }
        return ResponseMessage("token is not valid", user, 401)
    }

    fun authUser(userDto: UserDto): ResponseMessage {
        val user = userDbManager.getUserByLogin(userDto.login)
        if (user == null) {
            return ResponseMessage("user not found", user, 404)
        }
        if (user.password?.trim().equals(userDto.password.trim())) {
            val userToken = userTokenDbManager.getUserTokenByLogin(userDto.login)

            if (userToken != null) {
                return ResponseMessage("ok", userToken, 200)
            }
            val newUserToken = UserToken()
            newUserToken.login = userDto.login
            newUserToken.token = AuthToken.generateToken(80)
            userTokenDbManager.save(newUserToken)
            return ResponseMessage("ok", newUserToken, 200)
        }
        return ResponseMessage("Unauthorized", user,401)
    }

}