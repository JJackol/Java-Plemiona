package com.plemiona.restservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@SpringBootApplication
class RestServiceApplication {
}

fun main(args: Array<String>) {
    SpringApplication.run(RestServiceApplication::class.java, *args)
}

