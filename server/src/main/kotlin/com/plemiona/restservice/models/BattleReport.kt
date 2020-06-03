package com.plemiona.restservice.models

import javax.persistence.*

@Entity
@Table(name="battlereport")
data class BattleReport (
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "village_id")
        val defenderVillage: Village? = null,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "village_id")
        val attackingVillage: Village? = null,
        val attackerArmy: Int = 0,
        val defenderArmy: Int = 0,
        val attackerLoses: Int = 0,
        val defenderLoses: Int = 0,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)