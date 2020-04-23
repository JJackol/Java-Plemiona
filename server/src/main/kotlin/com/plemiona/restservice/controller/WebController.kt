package com.plemiona.restservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/")
class WebController {


    @GetMapping("save/")
    fun save(): String {
        return "player with nickname saved!"
    }
}