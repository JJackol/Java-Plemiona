package com.plemiona.restservice.repos

import com.plemiona.restservice.models.Building
import org.springframework.data.jpa.repository.JpaRepository

interface BuildingRepository : JpaRepository<Building, Long> {
}