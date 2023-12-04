package adventofcode_2023

import AbstractDay
import kotlin.math.pow

class Day4 : AbstractDay(4) {
    override fun question1(): Any {
        var result = 0

        input.forEach { line ->
            val card = parseInput(line)

            result += 2.0.pow(numberOfWinningHands(card) - 1).toInt()
        }
        return result
    }

    override fun question2(): Any {
        val cards = mutableListOf<Card>()
        var result = 0

        input.forEach { line ->
            val card = parseInput(line)
            cards.add(card)
        }

        cards.forEach { card ->
            for (i in card.id..<card.id + numberOfWinningHands(card)) {
                cards[i].count += card.count
            }
            result += card.count
        }

        return result
    }

    private fun numberOfWinningHands(card: Card): Int {
        return card.numbers.intersect(card.winningNumbers.toSet()).size
    }

    private fun parseInput(line: String): Card {
        val id = line.substringBefore(":").substringAfter(" ").trim().toInt()
        val winningNumbers = line.substringAfter(":").substringBefore("|").split(" ").mapNotNull { it.toIntOrNull() }
        val hand = line.substringAfter("|").split(" ").mapNotNull { it.toIntOrNull() }

        return Card(id, winningNumbers, hand)
    }

    class Card(
        val id: Int,
        val winningNumbers: List<Int>,
        val numbers: List<Int>,
    ) {
        var count = 1
    }
}