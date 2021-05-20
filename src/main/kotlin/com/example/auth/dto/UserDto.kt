package com.example.auth.dto

import javax.validation.constraints.*

class UserDto {
    @field:Size(min = 4, max = 15, message = "Длина должна быть от 4-х до 15-ти символов")
    @field:NotBlank
    val login: String = ""

    @field:NotBlank
    @field:Size(min = 8, max = 64, message = "Длина должна быть от 8-и до 64-х символов")
    @field:Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*\$ ")
    val password: String = ""
}

