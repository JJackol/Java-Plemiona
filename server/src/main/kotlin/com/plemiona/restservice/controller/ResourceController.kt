package com.plemiona.restservice.controller


import com.plemiona.restservice.models.Player
import com.plemiona.restservice.models.Village
import com.plemiona.restservice.repos.BuildingRepository
import com.plemiona.restservice.repos.PlayerRepository
import com.plemiona.restservice.repos.VillageRepository
import kotlinx.coroutines.flow.combineTransform
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("resources/")
class ResourceController(private val villageRepository: VillageRepository,
                         private val playerRepository: PlayerRepository,
                         private val buildingRepository: BuildingRepository)  {


    @GetMapping("mine/")
    fun mineResources(@RequestParam(value="playerid") playerid: Long,
                      @RequestParam(value="villageid") villageid: Long): Int {
        var player = playerRepository.findById(playerid).get()
        var village = villageRepository.findById(villageid).get()
        var mined = 0
        for (building in village.buildings) {
            village.resources += 100*building.level
            mined += 100*building.level
        }
        return mined
    }

    @GetMapping("recruit/")
    fun recruitArmy(@RequestParam(value="playerid") playerid: Long,
                    @RequestParam(value="villageid") villageid: Long,
                    @RequestParam(value="amount") amount: Int): HttpStatus {
        var player = playerRepository.findById(playerid).get()
        var village = villageRepository.findById(villageid).get()
        return if (village.resources < amount*50) {
            HttpStatus.BAD_REQUEST
        } else {
            village.resources -= amount*50
            village.soldiers += amount
            HttpStatus.ACCEPTED
        }
    }



}