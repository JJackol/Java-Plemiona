package com.plemiona.restservice.controller

import com.plemiona.restservice.models.Building
import com.plemiona.restservice.models.Player
import com.plemiona.restservice.models.Village
import com.plemiona.restservice.repos.BuildingRepository
import com.plemiona.restservice.repos.PlayerRepository
import com.plemiona.restservice.repos.VillageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/building")
class BuildingController(private val villageRepository: VillageRepository,
                        private val playerRepository: PlayerRepository,
                        private val buildingRepository: BuildingRepository) {

    @PostMapping("/build")
    fun buildBuilding(playerid: Long, villageid: Long, type: String): String {
        var player = playerRepository.findByIdOrNull(id = playerid)
        return if (player != null) {
            var village = player.village[0]
            var building = Building(village = village, type = type)
            "built"
        } else {
            "player not found"
        }
    }

    @PostMapping("/upgrade")
    fun upgradeBuilding(playerid: Long, villageid: Long, buildingid: Long): String {
        var player = playerRepository.findByIdOrNull(id = playerid)
        return if (player != null) {
            var village = player.village[0]
            var building = village.buildings.find { building -> building.id == buildingid }
            if (building != null) {
                upgradeBuilding(village, building)
            }
            "built"
        } else {
            "not found"
        }
    }

    @GetMapping("/")
    fun getVillageBuildings(playerid: Long, villageid: Long): String {
        var player = playerRepository.findByIdOrNull(id = playerid)
        return if (player != null) {
            var village = player.village[0]
            village.buildings.toString()
        } else {
            "player not found"
        }
    }

    fun upgradeBuilding(village: Village, building: Building): Boolean {
        return if (building.upgradeCost > village.resources) {
            building.level += 1
            true
        } else false
    }

}