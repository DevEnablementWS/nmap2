package com.okazukka.nmap2

import jakarta.persistence.*

@Entity(name = "toilets")
class ToiletEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val address: String,
    val area: String,

    @ManyToOne
    @JoinColumn(name = "maker_id")
    val maker: MakerEntity? = null,
)
