package adventofcode_2023

import AbstractDay

class Day4 : AbstractDay(4) {
    override fun question1(): Any {
        var result = 0

        input.forEach { line ->
            val card = parseInput(line)

            card.hand.forEach {
                if (card.winningNumbers.contains(it)) {
                    if (card.point == 0) {
                        card.point = 1
                    } else {
                        card.point *= 2
                    }
                }
            }
            result += card.point
        }
        return result
    }

    override fun question2(): Any {
        TODO("Not yet implemented")
    }

    private fun parseInput(line: String): Card {
        val id = line.substringBefore(":").substringAfter(" ").trim().toInt()
        val winningNumbers =
            line.substringAfter(":").substringBefore("|").trim().split("\\s+".toRegex()).map { it.toInt() }
        val hand = line.substringAfter("|").trim().split("\\s+".toRegex()).map { it.toInt() }

        return Card(id, winningNumbers, hand)
    }

    class Card(
        val id: Int,
        val winningNumbers: List<Int>,
        val hand: List<Int>,
    ) {
        var point = 0
    }
}