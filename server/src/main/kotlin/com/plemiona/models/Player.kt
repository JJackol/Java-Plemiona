package com.plemiona.models

import com.plemiona.models.Village
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name="player")
data class Player(
        val username: String,
        var passwordHash: String,
        var passwordSalt: String,
        var email: Email,
        @OneToMany(mappedBy = "player", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var villages: List<Village>,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)