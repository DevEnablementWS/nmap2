package com.okazukka.nmap2

class SpyToiletService: ToiletService {
    var toilets_wasCalled: Boolean = false
    var toilets_returnValue: List<Toilet> = emptyList()

    override fun toilets(): List<Toilet> {
        toilets_wasCalled = true
        return toilets_returnValue
    }
}

// (Test Doubles)
// Stub
// Spy
// Fake
// Mock
// Dummy
