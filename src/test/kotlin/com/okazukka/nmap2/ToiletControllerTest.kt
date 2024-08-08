package com.okazukka.nmap2

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockKExtension::class)
class ToiletControllerTest {
    lateinit var mockMvc: MockMvc
    @RelaxedMockK
    lateinit var spyToiletService: ToiletService


    @BeforeEach
    fun setup() {
        // Given
//        spyToiletService = SpyToiletService()
//        spyToiletService = mockk(relaxed = true)
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
            every { spyToiletService.toilets() } returns listOf(
                Toilet(1, "agurinmura", "nagakute"),
                Toilet(2, "toilet x", "nagoya"),
            )
//            spyToiletService.toilets_returnValue = listOf(
//                Toilet(1, "agurinmura", "nagakute"),
//                Toilet(2, "toilet x", "nagoya"),
//            )

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
            // fun myFunc(obj: MyClass)
            // class MyClass {
            //  var text: String
            // var number: Int
            // }
            verify { spyToiletService.add("agurinmura", "nagakute") }
//            verify { spyToiletService.add(match {
//                return@match it.contains("agu")
//            }, "nagakute") }
//            assertThat(spyToiletService.add_wasCalled).isTrue()
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
            verify {spyToiletService.add("agurinmura","nagakute")}
        }

        @Test
        fun `should call toilet service add method then return created toilet data`() {
            // Given
            every { spyToiletService.add("toilet-x", "address-x") } returns Toilet(5432, "toilet-x", "address-x")

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