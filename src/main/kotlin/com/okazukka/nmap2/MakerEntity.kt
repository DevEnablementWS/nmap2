package com.okazukka.nmap2

import jakarta.persistence.*


@Entity(name = "makers")
data class MakerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val companyName: String,

//    @OneToMany(mappedBy = "maker", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "maker", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val toilets: List<ToiletEntity> = mutableListOf(),
)
