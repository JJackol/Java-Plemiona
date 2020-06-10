package com.plemiona.restservice.controller

import com.plemiona.restservice.models.Player
import com.plemiona.restservice.repos.PlayerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
class PlayerController(private val playerRepository: PlayerRepository) {

    @GetMapping("/")
    fun findAll() = playerRepository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) =
            playerRepository.findByIdOrNull(id) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long) =
            playerRepository.deleteById(id) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser (@RequestParam(value="username") username: String,
                      @RequestParam(value="password") password: String,
                      @RequestParam(value="email") email: String) :String{
        var user = playerRepository.findByUsername(username)
        return if (user == null) {
            var player = Player(username = username, passwordHash = BCryptPasswordEncoder().encode(password), email = email)
            playerRepository.save(player)
            "User created"
        }
        else {
            "User already exist"
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun loginUser (username: String, password: String) : Boolean {
        var user = playerRepository.findByUsername(username)
        if (user != null){
            if (BCryptPasswordEncoder().matches(password, user.passwordHash)){
                return true
                "Welcome"
            }
            else{
                return false
                "Wrong password"
            }
        }
        else{
            return false
            "Wrong username"
        }
    }
}