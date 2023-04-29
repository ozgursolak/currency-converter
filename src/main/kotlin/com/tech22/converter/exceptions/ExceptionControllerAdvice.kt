package com.tech22.converter.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

data class ErrorModel(val message: String?, val cause: Throwable?)

@ControllerAdvice
@ResponseBody
class ExceptionControllerAdvice {
    
    @ExceptionHandler
    fun handleExternalServiceNotRespondingException(ex: ExternalServiceNotRespondingException): ResponseEntity<ErrorModel> {
        val errorModel = ErrorModel(message = ex.message, cause = ex.cause)
        return ResponseEntity(errorModel, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler
    fun handleRateNotFoundException(ex: RateNotFoundException): ResponseEntity<ErrorModel> {
        val errorModel = ErrorModel(message = ex.message, cause = null)
        return ResponseEntity(errorModel, HttpStatus.BAD_REQUEST)
    }
}