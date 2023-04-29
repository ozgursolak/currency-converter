package com.tech22.converter.exceptions

data class ExternalServiceNotRespondingException(
    override val message: String?,
    override val cause: Throwable?
    ) : Exception(message, cause)