package com.snakesandladders.service

import com.snakesandladders.data.Player
import org.junit.Assert.*
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.util.*
import java.util.stream.IntStream

class KotlinGameTest {

    private val game = KotlinGame()

    @Test
    fun testSelectPlayersMoreThan6() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { game.selectPlayers(7) }
    }

    @Test
    fun testSelectPlayersLessThan2() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { game.selectPlayers(1) }
    }

    @Test
    fun testSelectCorrectAmountOfPlayers() {
        val players: List<Player> = game.selectPlayers(4)
        assertNotNull(players)
        assertEquals(4, players.size)
        assertEquals(1, players[0].playerNumber)
        assertEquals(2, players[1].playerNumber)
        assertEquals(3, players[2].playerNumber)
        assertEquals(4, players[3].playerNumber)

        assertEquals(0, players[0].position)
        assertEquals(0, players[1].position)
        assertEquals(0, players[2].position)
        assertEquals(0, players[3].position)
    }

    @Test
    fun testRollDiceMethod() {
        val diceValues: MutableList<Int> = ArrayList()
        IntStream.range(0, 10000).forEach { _: Int -> diceValues.add(game.rollDice()) }
        val isDiceLessThanOnePresent = diceValues.stream().anyMatch { i: Int -> i < 1 }
        val isDiceMoreThanSixPresent = diceValues.stream().anyMatch { i: Int -> i > 6 }
        assertFalse(isDiceLessThanOnePresent)
        assertFalse(isDiceMoreThanSixPresent)
    }

    @Test
    fun testPlayAndGetWinner() {
        val players: List<Player> = game.selectPlayers(4)
        val winner: Player = game.defineWinner(players)
        assertNotNull(winner)
        assertTrue(winner.playerNumber > 0 && winner.playerNumber <= players.size)
        assertEquals(100, winner.position)
    }
}