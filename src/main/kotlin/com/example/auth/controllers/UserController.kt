package com.example.auth.controllers

import com.example.auth.dto.UserChangePasswordDto
import com.example.auth.dto.UserDto
import com.example.auth.entities.User
import com.example.auth.manager.UserManager
import com.example.auth.repository.UserRepository
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import java.io.Serializable


@RestController
class UserController(val userManager: UserManager) {


    @GetMapping("/users")
    fun index() = userManager.all()

    @PostMapping("/user/registration")
    @ResponseBody
    fun create(@RequestBody userDto: UserDto): ResponseEntity<User> {
       return ResponseEntity.ok(userManager.createUser(userDto))
    }

    @PostMapping("/user/auth")
    fun authUser(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        val response =  userManager.authUser(userDto)
        if (response.status != 200) {
            return ResponseEntity.status(response.status).body(response.message)
        }
        return ResponseEntity.ok(response.obj)
    }

    @PostMapping("/user/{login}")
    fun changePasswordUser(@RequestBody userChangePasswordDto: UserChangePasswordDto, @PathVariable login: String): ResponseEntity<Any> {
        val response = userManager.changePassword(login, userChangePasswordDto)
        if (response.status != 200) {
            return ResponseEntity.status(response.status).body(response.message)
        }
        return ResponseEntity.ok(response.obj)
    }

    @ExceptionHandler
    fun handle(e: MethodArgumentNotValidException): ResponseEntity<Errors> =
        ResponseEntity.badRequest().body(
            Errors(e.bindingResult.fieldErrors.map {
                Error(it.field, it.defaultMessage!!)
            }))

    data class Errors(val errors: List<Error>)
    data class Error(val field: String, val message: String)

}