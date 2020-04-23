package com.plemiona.repos

import com.plemiona.models.Soldier
import org.springframework.data.jpa.repository.JpaRepository

interface SoldierRepository : JpaRepository<Soldier, Long> {
}