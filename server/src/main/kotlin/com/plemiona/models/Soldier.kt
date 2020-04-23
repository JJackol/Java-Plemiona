package com.plemiona.models

import javax.persistence.*

@Entity
@Table(name="soldier")
data class Soldier (
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "village_id")
        var village: Village,
        val type: String,
        var amount: Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)