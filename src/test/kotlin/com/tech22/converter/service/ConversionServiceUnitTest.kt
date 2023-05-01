package com.tech22.converter.service

import com.tech22.converter.constant.ConversionRateConstant
import com.tech22.converter.payload.request.ConvertRequest
import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.Test

import com.tech22.converter.service.impl.ConverterServiceImpl
import com.tech22.converter.utils.ConversionRates

class ConversionServiceUnitTest {

    private val converterService = ConverterServiceImpl()
    private val validConvertRequest = ConvertRequest(convertFrom = "USD", convertTo = "EUR", amount = 100)

    @Test
    fun whenGetConversionResult_thenReturnConversion() {
        val url = ConversionRateConstant.URL
            .plus("?to=${validConvertRequest.convertTo}&from=${validConvertRequest.convertFrom}&amount=${validConvertRequest.amount}")

        mockkObject(ConversionRates)

        every { ConversionRates.getConversionResult(url) } returns 100.0

        val result = converterService.convert(validConvertRequest)

        assert(result == 100.0)
    }
}