package com.okazukka.nmap2

class SpyToiletService: ToiletService {
    var toilets_wasCalled: Boolean = false
    var add_wasCalled: Boolean = false
    var toilets_returnValue: List<Toilet> = emptyList()
    var add_arg_toilet: Toilet? = null

    override fun toilets(): List<Toilet> {
        toilets_wasCalled = true
        return toilets_returnValue
    }

    override fun add(toilet: Toilet) {
        add_wasCalled = true
        add_arg_toilet = toilet
    }
}

// ServiceA(Test Target) -> ModuleB

// (Test Doubles)
// Stub 95
// Spy

// Fake 3
// Dummy
// Mock
