package com.okazukka.nmap2

import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class ToiletServiceTest {
    @Autowired
    lateinit var toiletRepository: ToiletRepository
    @Autowired
    lateinit var makerRepository: MakerRepository

    @Autowired
    lateinit var entityManager: TestEntityManager

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
        val savedEntity = toiletRepository.saveAndFlush(toiletEntity)

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
        // Given

        // When
        toiletService.add(
            "okatoilet",
            "okasecret",
            )

        // Then
        val entities = toiletRepository.findAll()
        assertThat(entities.size).isEqualTo(1)
        assertThat(entities[0].name).isEqualTo("okatoilet")
        assertThat(entities[0].address).isEqualTo("okasecret")
    }
    @Test
    fun `should return data from service when add method is called`() {
        //When
        val actualToilet = toiletService.add(
            "okatoilet",
            "okasecret",
        )

        //Then
        val entities = toiletRepository.findAll()
        assertThat(entities.size).isEqualTo(1)
        assertThat(entities[0].id).isEqualTo(actualToilet.id)
        assertThat(entities[0].name).isEqualTo(actualToilet.name)
        assertThat(entities[0].address).isEqualTo(actualToilet.address)
    }

    @Test
    @Transactional
    fun `test`() {
        // Given
        val makerEntity = MakerEntity(
            id = 0,
            companyName = "TOTO",
        )
        val savedMakerEntity = makerRepository.saveAndFlush(makerEntity)

        val toiletEntity1 = ToiletEntity(
            id = 0,
            name = "name-1",
            address = "address-1",
            maker = savedMakerEntity,
        )
        val toiletEntity2 = ToiletEntity(
            id = 0,
            name = "name-2",
            address = "address-2",
            maker = savedMakerEntity,
        )
        toiletRepository.saveAndFlush(toiletEntity1)
        toiletRepository.saveAndFlush(toiletEntity2)

        entityManager.clear()


        val readMakerEntity = makerRepository.findAll().first()
        val readToiletEntities = toiletRepository.findAll()
        val toilets = readMakerEntity.toilets
        println("=======================")
        println(toilets)
        println(readToiletEntities)
        println("=======================")
    }
}
