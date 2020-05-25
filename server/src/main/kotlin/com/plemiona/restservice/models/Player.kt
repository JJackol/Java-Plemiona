package com.plemiona.restservice.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name="player")
data class Player(
        val username: String = "",
        var passwordHash: String = "",
        var passwordSalt: String = "",
        var email: String = "",
        @OneToMany(mappedBy = "player", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        @JsonManagedReference
        var village: List<Village> = emptyList(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)