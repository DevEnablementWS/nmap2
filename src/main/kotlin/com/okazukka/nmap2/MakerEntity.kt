package com.okazukka.nmap2

import jakarta.persistence.*


@Entity(name = "makers")
class MakerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val companyName: String,

    @OneToMany(mappedBy = "maker", cascade = [CascadeType.ALL])
    val toilets: MutableList<ToiletEntity> = mutableListOf(),
)
