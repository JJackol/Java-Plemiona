package com.plemiona.restservice.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.io.Serializable
import javax.persistence.*


@Entity @Table(name="village")
data class Village(
        var points: Int = 0,
        var name: String = "",
        var resources: Int = 0,
        var soldiers: Int = 0,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "player_id")
        @JsonBackReference
        var player: Player? = null,
        @OneToMany(mappedBy = "village", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var buildings: MutableList<Building> = mutableListOf(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 1
)