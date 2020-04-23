package com.plemiona.models

import javax.persistence.*

@Entity @Table(name="village")
data class Village(
        var points: Int,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "player_id")
        var player: Player? = null,
        @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var resources: List<Resource> = emptyList(),
        @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var buildings: List<Building> = emptyList(),
        @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var soldiers: List<Soldier> = emptyList(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)