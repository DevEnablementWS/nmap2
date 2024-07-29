package com.okazukka.nmap2

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class ToiletControllerTest {
    lateinit var mockMvc: MockMvc
    lateinit var spyToiletService: SpyToiletService


    @BeforeEach
    fun setup() {
        // Given
        spyToiletService = SpyToiletService()
        mockMvc = MockMvcBuilders.standaloneSetup(
            ToiletController(
                spyToiletService
            )
        ).build()
    }

    @Test
    fun getReturns200OK() {
        // When
        val response = mockMvc.perform(get("/api/toilets"))

        // Then
        response
            .andExpect(status().isOk)
    }

    @Test
    fun getReturnsToiletList() {
        // When
        val response = mockMvc.perform(get("/api/toilets"))

        // Then
        response
            .andExpect(jsonPath("$.length()", equalTo(1)))
            .andExpect(jsonPath("$[0].id", equalTo(1)))
            .andExpect(jsonPath("$[0].name", equalTo("agurinmura")))
            .andExpect(jsonPath("$[0].address", equalTo("nagakute")))
    }

    @Test
    fun `should call ToiletService toilets method`() {
        // When
        mockMvc.perform(get("/api/toilets"))

        // Then
        assertThat(spyToiletService.toilets_wasCalled).isTrue()
    }

    @Test
    fun `should return ToiletService toilets List`() {
        // Given
        spyToiletService.toilets_returnValue = listOf(
            Toilet(1, "agurinmura", "nagakute"),
            Toilet(2, "toilet x", "nagoya"),
        )

        // When
        val response = mockMvc.perform(get("/api/toilets"))

        // Then
        response
            .andExpect(jsonPath("$.length()", equalTo(2)))
            .andExpect(jsonPath("$[0].id", equalTo(1)))
            .andExpect(jsonPath("$[0].name", equalTo("agurinmura")))
            .andExpect(jsonPath("$[0].address", equalTo("nagakute")))
            .andExpect(jsonPath("$[1].id", equalTo(2)))
            .andExpect(jsonPath("$[1].name", equalTo("toilet x")))
            .andExpect(jsonPath("$[1].address", equalTo("nagoya")))
    }
}