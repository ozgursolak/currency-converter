package com.tech22.converter.controller

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.clear
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import com.tech22.converter.payload.request.ConvertRequest
import com.tech22.converter.service.ConverterService
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch

@WebMvcTest
class ConversionControllerUnitTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var converterService: ConverterService

    private val validConvertRequest = ConvertRequest(convertFrom = "USD", convertTo = "EUR", amount = 100)

    @Test
    fun converterController_whenGetRequest_thenReturnsAmountJsonWithStatus200() {
        every { converterService.convert(validConvertRequest) } returns 100.0;

        val mvcResult = mockMvc.perform(get("/api/convert?convertFrom=USD&convertTo=EUR&amount=100"))
            .andExpect(status().isOk)
            .andExpect(request().asyncStarted())
            .andReturn()

        mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string("""{"result":100.0}"""))
    }

    @Test
    fun converterController_whenGetRequestWithInvalidRequest_thenReturnsAmountJsonWithStatus400() {
        mockMvc.perform(get("/api/convert?convertFrom=USD&convertTo=EUR&amount=-100"))
            .andExpect(status().isBadRequest)
    }
}
