package com.tech22.converter.service.impl

import org.springframework.stereotype.Service

import com.tech22.converter.service.ConverterService
import com.tech22.converter.payload.request.ConvertRequest
import com.tech22.converter.utils.ConversionRates
import com.tech22.converter.constant.ConversionRateConstant

@Service
class ConverterServiceImpl: ConverterService {

    override fun convert(convertRequest: ConvertRequest): Double {
        val (convertFrom, convertTo, amount) = convertRequest
        val url = ConversionRateConstant.URL.plus("?to=${convertTo}&from=${convertFrom}&amount=${amount}")

        return ConversionRates.getConversionResult(url)
    }
}