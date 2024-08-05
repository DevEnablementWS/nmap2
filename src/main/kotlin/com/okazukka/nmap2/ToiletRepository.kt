package com.okazukka.nmap2

import org.springframework.data.jpa.repository.JpaRepository

interface ToiletRepository: JpaRepository<ToiletEntity, Long>
interface MakerRepository: JpaRepository<MakerEntity, Long>
