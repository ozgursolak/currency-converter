package com.tech22.converter.utils

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

import com.tech22.converter.constant.ConversionRateConstant
import com.tech22.converter.exceptions.ExternalServiceNotRespondingException
import com.tech22.converter.exceptions.RateNotFoundException
import com.tech22.converter.model.ConversionResult

class ConversionRates {
    companion object {
        private val client = HttpClient.newBuilder().build()
        private val mapper = ObjectMapper().registerModule(KotlinModule())

        fun getConversionResult(url: String): Double {
            val response = getDataFromConversionAPI(url)
            val conversionResult = mapper.readValue(response, ConversionResult::class.java)

            if(!conversionResult.success && conversionResult.error != null)
            {
                throw RateNotFoundException(message = conversionResult.error.info)
            }

            return conversionResult.result;
        }

        private fun getDataFromConversionAPI(url: String): String {
            val request =  HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("apikey",  ConversionRateConstant.API_KEY)
                .build()

            try {
                val response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())

                return response.get().body()
            }
            catch (error: Error) {
                throw ExternalServiceNotRespondingException(message = error.message, cause = error.cause)
            }
        }
    }
}