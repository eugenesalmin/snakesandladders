package com.snakesandladders.controller

import com.snakesandladders.data.Player
import com.snakesandladders.service.KotlinGame
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController {

    val kg = KotlinGame();

    @GetMapping("/roll-dice")
    fun rollDice() = kg.rollDice();

    @PostMapping("/select-players")
    fun selectPlayers(@RequestParam(value = "amount", defaultValue = "2") amount: Int): List<Player> {
        return kg.selectPlayers(amount)
    }

    @PostMapping("/select-players-and-play")
    fun selectPlayersAndPlay(@RequestParam(value = "amount", defaultValue = "2") amount: Int): Player {
        val players = kg.selectPlayers(amount)

        return kg.defineWinner(players)
    }

}