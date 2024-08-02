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
    fun get(): List<GetToiletResponseData> {
        return toiletService.toilets().map { GetToiletResponseData(it.id, it.name, it.address) }
    }

    @GetMapping("/okazukka")
    fun getOkazukka(): String {
        return "Hello okazukka!!"
    }

    @PostMapping
    fun post(@RequestBody requestBody: PostToiletRequestBody): PostToiletResponseData {
        val toilet = toiletService.add(requestBody.name, requestBody.address)
        return PostToiletResponseData(toilet.id, toilet.name, toilet.address)
    }
}

data class GetToiletResponseData(
    val id: Long,
    val name: String,
    val address: String,
)

data class PostToiletRequestBody(
    val name: String,
    val address: String,
)

data class PostToiletResponseData(
    val id: Long,
    val name: String,
    val address: String,
)

//(
//'1'
//'あぐりん村',
//'長久手市前熊下田１３４',
//)