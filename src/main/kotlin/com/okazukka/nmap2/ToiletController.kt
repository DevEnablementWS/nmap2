package com.okazukka.nmap2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/toilets")
class ToiletController(
    val toiletService: ToiletService,
) {
    @GetMapping
    fun get(): List<Toilet> {
        return toiletService.toilets()
    }

    @PostMapping
    fun post(@RequestBody toilet: Toilet) {
        toiletService.add(toilet)
    }
}



//(
//'1'
//'あぐりん村',
//'長久手市前熊下田１３４',
//)