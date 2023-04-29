package com.tech22.converter.payload.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

data class ConvertRequest(
    @field:Size(min = 3, max = 3, message = "Currency iso code has to be length of 3")
    val convertFrom: String,
    @field:Size(min = 3, max = 3, message = "Currency iso code has to be length of 3")
    val convertTo: String,
    @field:Min(value = 0, message = "Amount cannot be less than  0")
    val amount: Int,
)
