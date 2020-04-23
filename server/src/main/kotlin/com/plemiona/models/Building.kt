package com.plemiona.models

import javax.persistence.*

@Entity
@Table(name="building")
data class Building (
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "village_id")
        val village: Village,
        val type: String,
        var level: Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)