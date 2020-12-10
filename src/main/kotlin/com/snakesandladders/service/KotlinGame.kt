package com.snakesandladders.service

import com.snakesandladders.data.Player
import org.slf4j.LoggerFactory
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random

class KotlinGame {
    val logger = LoggerFactory.getLogger(this.javaClass)

    fun rollDice(): Int {
        return Random.nextInt(1, 7)
    }

    fun selectPlayers(playersAmount: Int): List<Player> {
        require(!(playersAmount > 6 || playersAmount < 2)) { "Please enter players amount between 2 and 6 inclusive" }
        return IntStream.rangeClosed(1, playersAmount).boxed().map { t -> Player(t, 0) }.collect(Collectors.toList())
    }

    fun defineWinner(players: List<Player>): Player {
        while (true) {
            for (player in players) {
                val newPosition = player.position + rollDice()
                if (newPosition == 100) {
                    logger.info("Player {} won !", player.playerNumber)

                    player.position = newPosition
                    return player
                }
                if (newPosition < 100) {
                    logger.info("Player's {} new position: {}", player.playerNumber, newPosition)
                    player.position = newPosition;
                }
                if (newPosition > 100) {
                    logger.info("Player's {} remains on position: {}", player.playerNumber, player.position)
                }
            }
        }
    }
}