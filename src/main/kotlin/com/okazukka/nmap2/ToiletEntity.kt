package com.okazukka.nmap2

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity(name = "toilets")
data class ToiletEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: Int,
    val address: Int,
    val toiletNumber: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maker_id")
    val maker: MakerEntity? = null,
)

@Entity(name = "makers")
data class MakerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val companyName: String,

    @OneToMany(fetch = FetchType.LAZY)
    val toilets: List<ToiletEntity>,
)
