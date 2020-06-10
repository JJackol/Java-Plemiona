package com.plemiona.restservice.controller

import com.plemiona.restservice.repos.PlayerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
class PlayerController(private val repository: PlayerRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) =
            repository.findByIdOrNull(id) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long) =
            repository.deleteById(id) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
}