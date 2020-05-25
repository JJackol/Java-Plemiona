package com.plemiona.restservice.models

import javax.persistence.*

@Entity
@Table(name="resource")
data class Resource (
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "village_id")
        val village: Village,
        val type: String,
        var amount: Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)