package com.plemiona.restservice.repos

import com.plemiona.restservice.models.Soldier
import org.springframework.data.jpa.repository.JpaRepository

interface SoldierRepository : JpaRepository<Soldier, Long> {
}