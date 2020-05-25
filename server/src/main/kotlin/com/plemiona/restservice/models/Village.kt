package com.plemiona.restservice.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.io.Serializable
import javax.persistence.*


@Entity @Table(name="village")
data class Village(
        var points: Int = 0,
        var name: String = "",
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "player_id")
        @JsonBackReference
        var player: Player? = null,
        @OneToMany(mappedBy = "village", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var resources: Set<Resource> = emptySet(),
        @OneToMany(mappedBy = "village", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var buildings: Set<Building> = emptySet(),
        @OneToMany(mappedBy = "village", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var soldiers: Set<Soldier> = emptySet(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 1
)