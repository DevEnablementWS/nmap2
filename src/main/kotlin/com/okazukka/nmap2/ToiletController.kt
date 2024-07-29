package com.okazukka.nmap2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ToiletController(
    val toiletService: ToiletService,
) {
    @GetMapping("/api/toilets")
    fun get(): List<Toilet> {
        return toiletService.toilets()
    }
}



//(
//'1'
//'あぐりん村',
//'長久手市前熊下田１３４',
//)