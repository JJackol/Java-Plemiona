package com.plemiona.repos

import com.plemiona.models.Village
import org.springframework.data.jpa.repository.JpaRepository

interface VillageRepository : JpaRepository<Village, Long> {
}