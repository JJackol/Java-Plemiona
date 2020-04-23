package com.plemiona.restservice.models

import javax.persistence.*

@Entity
@Table(name="player")
data class Player(
        val username: String,
        var passwordHash: String,
        var passwordSalt: String,
        var email: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)