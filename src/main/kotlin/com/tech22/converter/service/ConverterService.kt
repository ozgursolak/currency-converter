package com.tech22.converter.service

import com.tech22.converter.payload.request.ConvertRequest

interface ConverterService {
    fun convert(convertRequest: ConvertRequest): Double
}