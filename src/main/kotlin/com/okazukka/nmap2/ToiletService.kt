package com.okazukka.nmap2

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ToiletService {
    fun toilets(): List<Toilet>
    fun add(name: String, address: String): Toilet
}


@Service
@Transactional
class ToiletServiceImpl(private val toiletRepository: ToiletRepository) : ToiletService {
    override fun toilets(): List<Toilet> {
        return toiletRepository.findAll().map { Toilet(it.id, it.name, it.address) }
    }

    override fun add(name: String, address: String): Toilet {
        val entity = toiletRepository.save(ToiletEntity(0, name, address, "Aichi"))
        return Toilet(entity.id, entity.name, entity.address)
    }
}


// Controller --add()--> ToiletService --save()--> ToiletRepository --> Database
// 1. Use Test Doubles
// Good:
// Bad:

// 2. Use Real Database
