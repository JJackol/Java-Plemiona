package com.plemiona.restservice.controller;

import com.plemiona.restservice.models.Player
import com.plemiona.restservice.models.Village
import com.plemiona.restservice.repos.PlayerRepository
import com.plemiona.restservice.repos.VillageRepository;
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/village")
class VillageController(private val villageRepository: VillageRepository,
                        private val playerRepository: PlayerRepository) {

    @GetMapping("/")
    fun findAll() = villageRepository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) =
            villageRepository.findByIdOrNull(id) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This village does not exist")

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePlayerVillage(playerid: Long, villageName: String): String {
        var player = playerRepository.findByIdOrNull(playerid)
        return if (player != null)
        {
            var village = Village(0, name = villageName, player = player)
            villageRepository.save(village)
            "village created"
        } else {
            "no player found"
        }
    }

}
