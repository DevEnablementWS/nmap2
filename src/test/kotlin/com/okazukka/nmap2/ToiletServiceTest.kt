package com.okazukka.nmap2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest

@DataJpaTest
class ToiletServiceTest {
    @Autowired
    lateinit var toiletRepository: ToiletRepository

    lateinit var toiletService: ToiletService

    @BeforeEach
    fun setup() {
        toiletService = ToiletServiceImpl(
            toiletRepository,
        )
    }

    @Test
    fun `should return data fetched from repository when toilets method is called`() {
        // Given
        val toiletEntity = ToiletEntity(
            id = 0,
            name = "name-1",
            address = "address-1",
        )
        val savedEntity = toiletRepository.save(toiletEntity)

        // When
        val actualToilets = toiletService.toilets()

        // Then
        assertThat(actualToilets).isEqualTo(
            listOf(
                Toilet(
                    id = savedEntity.id,
                    name = "name-1",
                    address = "address-1",
                )
            )
        )
    }
    @Test
    fun `should save given toilet in database`() {
        //Given

        //When
        toiletService.add(Toilet(2, "okatoilet", "okasecret"))

        //Then
        assertThat(toiletRepository.findAll()).isEqualTo(listOf(ToiletEntity(2, "okatoilet", "okasecret")))
    }
}