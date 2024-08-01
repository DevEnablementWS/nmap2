package com.okazukka.nmap2

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
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

    @Nested
    inner class Get {
        @Test
        fun getReturns200OK() {
            // When
            val response = mockMvc.perform(get("/api/toilets"))

            // Then
            response
                .andExpect(status().isOk)
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

    @Nested
    inner class Post {
        @Test
        fun `post returns 200 OK`() {
            // When
            mockMvc.post("/api/toilets") {
                contentType = MediaType.APPLICATION_JSON
                content =
                    """
                    {
                    "name": "agurinmura",
                    "address": "nagakute"
                    }
                """.trimIndent()
            }
                .andExpect { status().isOk }
        }

        @Test
        fun `should called ToiletService add method`() {
            // When
            mockMvc.post("/api/toilets") {
                contentType = MediaType.APPLICATION_JSON
                content =
                    """
                    {
                    "name": "agurinmura",
                    "address": "nagakute"
                    }
                """.trimIndent()
            }

            // Then
            assertThat(spyToiletService.add_wasCalled).isTrue()
        }

        @Test
        fun `should give toilet service add method given request body`() {
            // When
            mockMvc.post("/api/toilets") {
                contentType = MediaType.APPLICATION_JSON
                content =
                    """
                    {
                    "name": "agurinmura",
                    "address": "nagakute"
                    }
                """.trimIndent()
            }

            // Then
            assertThat(spyToiletService.add_arg_name)
                .isEqualTo("agurinmura")
            assertThat(spyToiletService.add_arg_address)
                .isEqualTo("nagakute")
        }

        @Test
        fun `should call toilet service add method then return created toilet data`() {
            // Given
            spyToiletService.add_returnValue = Toilet(5432, "toilet-x", "address-x")

            // When
            mockMvc.post("/api/toilets") {
                contentType = MediaType.APPLICATION_JSON
                content =
                    """
                    {
                    "name": "toilet-x",
                    "address": "address-x"
                    }
                """.trimIndent()
            }
                .andExpect {
                    jsonPath("$.id", equalTo(5432))
                    jsonPath("$.name", equalTo("toilet-x"))
                    jsonPath("$.address", equalTo("address-x"))
                }

        }
    }
}