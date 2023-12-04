package adventofcode_2023

import AbstractDay

class Day4 : AbstractDay(4) {
    override fun question1(): Any {
        var result = 0

        input.forEach { line ->
            val card = parseInput(line)

            card.hand.forEach {
                if (card.winningNumbers.contains(it)) {
                    if (card.score == 0) {
                        card.score = 1
                    } else {
                        card.score *= 2
                    }
                }
            }
            result += card.score
        }
        return result
    }

    override fun question2(): Any {
        var result = 0
        val cards = mutableListOf<Card>()

        input.forEach { line ->
            val card = parseInput(line)
            card.numberOfWinningHand = numberOfWinningHands(card)
            cards.add(card)
        }


        var index = 0
        while (index < cards.size) {
            val card = cards[index]

            for (i in card.id..<card.id + card.numberOfWinningHand) {
                cards.addLast(cards[i])
            }

            index++
        }

        return cards.groupingBy { it.id }.eachCount().values.sum()
    }

    private fun numberOfWinningHands(card: Card): Int {
        var result = 0

        card.hand.intersect(card.winningNumbers.toSet()).forEach { _ ->
            result++
        }

        return result
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
        var score = 0
        var numberOfWinningHand = 0
        var count = 0
    }
}