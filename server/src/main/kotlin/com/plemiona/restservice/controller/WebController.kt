package com.plemiona.restservice.controller

import com.plemiona.restservice.models.Player
import com.plemiona.restservice.models.Village
import com.plemiona.restservice.repos.PlayerRepository
import com.plemiona.restservice.repos.VillageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("api/")
class WebController {

    @Autowired
    lateinit var playerRepository: PlayerRepository
    @Autowired
    lateinit var villageRepository: VillageRepository

    @GetMapping("savePlayer/")
    fun savePlayer(): String {
        var player = Player("test", "test", "test", "test")
        playerRepository.save(player)
        return "player with " + player.username + " nickname saved!"
    }

}