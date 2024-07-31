package com.okazukka.nmap2

class SpyToiletService: ToiletService {
    var toilets_wasCalled: Boolean = false
    var add_wasCalled: Boolean = false
    var toilets_returnValue: List<Toilet> = emptyList()
    var add_arg_name: String? = null
    var add_arg_address: String? = null
    var add_returnValue: Toilet = Toilet(0, "", "")

    override fun toilets(): List<Toilet> {
        toilets_wasCalled = true
        return toilets_returnValue
    }

    override fun add(name: String, address: String): Toilet {
        add_wasCalled = true
        add_arg_name = name
        add_arg_address = address
        return add_returnValue
    }

}

// ServiceA(Test Target) -> ModuleB

// (Test Doubles)
// Stub 95
// Spy

// Fake 3
// Dummy
// Mock
