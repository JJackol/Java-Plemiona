package com.plemiona.restservice.controller

import com.plemiona.restservice.models.Building
import com.plemiona.restservice.models.BuildingType
import com.plemiona.restservice.models.Village
import com.plemiona.restservice.repos.BuildingRepository
import com.plemiona.restservice.repos.PlayerRepository
import com.plemiona.restservice.repos.VillageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/building")
class BuildingController(private val villageRepository: VillageRepository,
                        private val playerRepository: PlayerRepository,
                        private val buildingRepository: BuildingRepository) {

    @PostMapping("/build")
    fun buildBuilding(@RequestParam(value="playerid") playerid: Long,
                      @RequestParam(value="villageid") villageid: Long,
                      @RequestParam(value="buildingType")buildingType: String): String {
        var player = playerRepository.findByIdOrNull(id = playerid)
        return if (player != null) {
            var village = player.village[0]
            var building = Building(village = village, type = BuildingType.from(buildingType))
            return if (village.resources > building.upgradeCost) {
                buildingRepository.save(building)
                "built"
            } else {
                "not enough resources"
            }
        } else {
            "player not found"
        }
    }

    @PostMapping("/upgrade")
    fun upgradeBuilding(@RequestParam(value="playerid")playerid: Long,
                        @RequestParam(value="villageid") villageid: Long,
                        @RequestParam(value="buildingid") buildingid: Long): String {
        var player = playerRepository.findByIdOrNull(id = playerid)
        return if (player != null) {
            var village = player.village[0]
            var building = village.buildings.find { building -> building.id == buildingid }
            if (building != null) {
                if (village.resources < building.upgradeCost){
                        upgradeBuilding(village, building)
                } else {
                    "not enough resources"
                }
            }
            villageRepository.save(village)
            "upgraded"
        } else {
            "not found"
        }
    }

    @GetMapping("/")
    fun getVillageBuildings(@RequestParam(value="playerid") playerid: Long,
                            @RequestParam(value="villageid") villageid: Long): String {
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