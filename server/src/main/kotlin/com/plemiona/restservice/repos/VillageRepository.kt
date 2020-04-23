package com.plemiona.restservice.repos

import com.plemiona.restservice.models.Village
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VillageRepository : JpaRepository<Village, Long> {
}