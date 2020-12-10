package com.snakesandladders.integration

import com.snakesandladders.data.Player
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @Autowired
    lateinit var rt: TestRestTemplate

    @Test
    fun `Assert roll dice works`() {
        val entity = rt.getForEntity<String>("/roll-dice", String::class.java)
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).containsOnlyDigits()
    }


    @Test
    fun `Assert roll select players works`() {
        val entity = rt.postForEntity("/select-players", "", String::class.java)
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).contains("[{\"playerNumber\":1,\"position\":0},{\"playerNumber\":2,\"position\":0}]")
    }

    @Test
    fun `Assert full game lifecycle works`() {
        val entity = rt.postForEntity("/select-players-and-play", "", Player::class.java)
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).isIn(Player(1, 100), Player(2, 100))
    }

}