package com.plemiona.restservice.models

import javax.persistence.*

@Entity @Table(name="village")
data class Village(
        var points: Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)