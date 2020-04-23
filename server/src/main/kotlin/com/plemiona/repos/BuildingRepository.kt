package com.plemiona.repos

import com.plemiona.models.Building
import org.springframework.data.jpa.repository.JpaRepository

interface BuildingRepository : JpaRepository<Building, Long> {
}