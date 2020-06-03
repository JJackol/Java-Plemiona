package com.plemiona.restservice.repos

import com.plemiona.restservice.models.BattleReport
import com.plemiona.restservice.models.Building
import org.springframework.data.jpa.repository.JpaRepository

interface BattleReportRepository : JpaRepository<BattleReport, Long> {
}