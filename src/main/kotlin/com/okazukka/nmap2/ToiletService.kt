package com.okazukka.nmap2

import org.springframework.stereotype.Service

interface ToiletService {
    fun toilets(): List<Toilet>
}

@Service
class ToiletServiceImpl: ToiletService {
    override fun toilets(): List<Toilet> {
        TODO("Not yet implemented")
    }
}