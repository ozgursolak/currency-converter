package com.tech22.converter.exceptions

data class RateNotFoundException(
    override val message: String,
    ) : Exception(message)