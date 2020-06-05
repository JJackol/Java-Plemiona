package com.plemiona.restservice.models

import javax.persistence.*


interface IQuality {
        fun getQuality(): Int
}


enum class BuildingType(val type: String): IQuality {
        Mill("mill"){
                override fun getQuality() = 2
        },
        Forge("forge"){
                override fun getQuality() = 3
        },
        Farm("farm"){
                override fun getQuality() = 1
        },
        Guildhall("Guildhall") {
                override fun getQuality() = 4
        };

        companion object {
                fun from(findValue: String): BuildingType = BuildingType.values().first { it.type == findValue }
        }
}

@Entity
@Table(name="building")
data class Building(
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "village_id")
        val village: Village? = null,
        val type: BuildingType = BuildingType.Guildhall,
        var level: Int = 1,
        var upgradeCost: Int = level*type.getQuality(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)