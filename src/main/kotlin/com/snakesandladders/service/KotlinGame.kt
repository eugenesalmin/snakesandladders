package com.snakesandladders.service

import com.snakesandladders.data.Player
import org.slf4j.LoggerFactory
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random

class KotlinGame {
    val logger = LoggerFactory.getLogger(this.javaClass)

    fun rollDice(): Int {
        return -1;
    }

    fun selectPlayers(playersAmount: Int): List<Player> {
        return emptyList();
    }

    fun defineWinner(players: List<Player>): Player {
      return Player(-1, -1)
    }
}