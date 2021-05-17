package com.example.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

data class UserChangePasswordDto(
    @JsonProperty("old_password")
    val oldPassword: String,

    @JsonProperty("new_password")
    val newPassword: String,

    @JsonProperty("new_confirmed_password")
    val newConfirmedPassword: String,

    @JsonProperty("token")
    val token: String


)
