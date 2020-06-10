package com.plemiona.restservice.controller;

import com.plemiona.restservice.models.BattleReport
import com.plemiona.restservice.models.Building
import com.plemiona.restservice.models.Player
import com.plemiona.restservice.models.Village
import com.plemiona.restservice.repos.BattleReportRepository
import com.plemiona.restservice.repos.BuildingRepository
import com.plemiona.restservice.repos.PlayerRepository
import com.plemiona.restservice.repos.VillageRepository;
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/village")
class VillageController(private val villageRepository: VillageRepository,
                        private val playerRepository: PlayerRepository,
                        private val buildingRepository: BuildingRepository,
                        private val battleReportRepository: BattleReportRepository) {

    @GetMapping("/")
    fun findAll() = villageRepository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) =
            villageRepository.findByIdOrNull(id) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This village does not exist")

    @GetMapping("/playerVillages/{id}")
    fun findPlayerVillages(@PathVariable id: Long) =
            villageRepository.findAll().filter { village -> village.player == playerRepository.findByIdOrNull(id) }
                    ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This player has no villages or doesnt exist")

    @PostMapping("attack/{id}")
    fun attackVillage(@PathVariable id: Long, @RequestParam(value="playerid") playerid: Long,
                      @RequestParam(value="villageid") villageid: Long,
                      @RequestParam(value="armySize") armySize: Int): BattleReport {
        var defenderVillage = villageRepository.findById(id).get()
        var attackingVillage = villageRepository.findById(villageid).get()
        var defenderArmy = defenderVillage.soldiers
        if (defenderVillage.soldiers < armySize) {
            defenderVillage.soldiers = 0
            attackingVillage.soldiers -= defenderVillage.soldiers
        } else {
            defenderVillage.soldiers -= armySize
            attackingVillage.soldiers -= armySize
        }
        val battleReport = BattleReport(defenderVillageid = defenderVillage.id,
                                        attackingVillageid = attackingVillage.id,
                                        attackerid = attackingVillage.player?.id!!,
                                        defenderid = defenderVillage.player?.id!!,
                                        attackerArmy = armySize,
                                        defenderArmy = defenderArmy,
                                        attackerLoses = armySize - defenderArmy,
                                        defenderLoses = defenderArmy - armySize)
        //villageRepository.save(defenderVillage)
        //villageRepository.save(attackingVillage)
        battleReportRepository.save(battleReport)
        return battleReport
    }

    @GetMapping("/playerBattleReports/{id}")
    fun findPlayerBattleReports(@PathVariable id: Long) =
            battleReportRepository.findAll().filter {
                battleReport -> battleReport.attackerid == id ||
                    battleReport.defenderid == id}
                    ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This player has no villages or doesnt exist")

    @GetMapping("/playerBattleReports")
    fun findAllBattleReports() = battleReportRepository.findAll()


    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePlayerVillage(@PathVariable id: Long, @RequestParam(value="villageName") villageName: String): String {
        val player = playerRepository.findById(id).get()
        return if (player != null)
        {
            var village = Village(name = villageName, player = player)
            //villageRepository.save(village)
            val building = Building(village = village)
            buildingRepository.save(building)
            "village created for" + player.username
        } else {
            "no player found"
        }
    }

}
