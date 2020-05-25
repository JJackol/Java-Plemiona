package com.plemiona.restservice.models

import javax.persistence.*

@Entity
@Table(name="building")
data class Building (
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "village_id")
        val village: Village? = null,
        val type: String = "",
        var level: Int = 1,
        var upgradeCost: Int = level*1000,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)