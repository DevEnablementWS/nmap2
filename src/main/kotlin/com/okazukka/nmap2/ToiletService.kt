package com.okazukka.nmap2

import org.springframework.stereotype.Service

interface ToiletService {
    fun toilets(): List<Toilet>
    fun add(toilet: Toilet)
}


@Service
class ToiletServiceImpl(private val toiletRepository: ToiletRepository) : ToiletService {
    override fun toilets(): List<Toilet> {
        return toiletRepository.findAll().map { Toilet(it.id, it.name, it.address) }
    }

    override fun add(toilet: Toilet) {
        toiletRepository.save(ToiletEntity(toilet.id, toilet.name, toilet.address))
    }
}


// Controller --add()--> ToiletService --save()--> ToiletRepository --> Database
// 1. Use Test Doubles
// Good:
// Bad:

// 2. Use Real Database
