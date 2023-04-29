package com.tech22.converter.controller

import lombok.RequiredArgsConstructor
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestMapping

import com.tech22.converter.service.ConverterService
import com.tech22.converter.payload.request.ConvertRequest
import com.tech22.converter.payload.response.ConversionClientResult

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class ConverterController(val service: ConverterService) {

    @GetMapping("/convert")
    @ResponseStatus(HttpStatus.OK)
    suspend fun convert(@Valid convertRequest: ConvertRequest): ResponseEntity<ConversionClientResult> {
        val result = service.convert(convertRequest)
        val conversionClientResult = ConversionClientResult(result = result)

        return ResponseEntity.ok(conversionClientResult)
    }
}