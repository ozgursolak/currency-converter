package com.tech22.converter.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class ConversionError(val code: Int, val info: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ConversionResult(
    @JsonProperty("success") val success: Boolean,
    @JsonProperty("result") val result: Double,
    @JsonProperty("error") val error: ConversionError?,
)


