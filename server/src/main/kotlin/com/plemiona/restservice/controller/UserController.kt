package com.plemiona.restservice.controller

import com.plemiona.restservice.models.Player
import com.plemiona.restservice.repos.PlayerRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/userr")
class UserController (private val playerRepository: PlayerRepository) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser (username: String, password: String, email: String) :String{
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