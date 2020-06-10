package com.plemiona.restservice.models

import javax.persistence.*

@Entity
@Table(name="battlereport")
data class BattleReport (
        val defenderVillageid: Long = 0,
        val attackingVillageid: Long = 0,
        val defenderid: Long = 0,
        val attackerid: Long = 0,
        val attackerArmy: Int = 0,
        val defenderArmy: Int = 0,
        val attackerLoses: Int = 0,
        val defenderLoses: Int = 0,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)