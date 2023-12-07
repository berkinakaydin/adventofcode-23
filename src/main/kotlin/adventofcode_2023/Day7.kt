package adventofcode_2023

import AbstractDay

class Day7 : AbstractDay(7) {
    override fun question1(): Any {
        val hands = mutableListOf<Hand>()

        input.forEach { line ->
            val cards = line.split(" ")[0].map { Card(it) }
            val bid = line.split(" ")[1].toInt()

            val hand = Hand(bid, cards)
            hand.strength = calculateStrengthQ1(cards)
            hands.add(hand)
        }

        processCards(hands)

        return calculateResult(hands)
    }

    override fun question2(): Any {
        val hands = mutableListOf<Hand>()

        input.forEach { line ->
            val cards = line.split(" ")[0].map { Card(it) }
            val bid = line.split(" ")[1].toInt()

            cards.map { if (it.name == 'J') it.value = 1 else it.value }

            val hand = Hand(bid, cards)
            hand.strength = calculateStrengthQ2(cards)
            hands.add(hand)
        }

        processCards(hands)

        return calculateResult(hands)
    }

    private fun calculateResult(hands: List<Hand>): Int {
        var result = 0
        hands.forEach { hand ->
            result += hand.bid * hand.rank
        }
        return result
    }

    private fun processCards(hands: List<Hand>) {
        for (i in hands.indices) {
            for (j in i + 1..<hands.size) {
                if (hands[i].strength > hands[j].strength) {
                    hands[i].rank++
                } else if (hands[i].strength == hands[j].strength) {
                    resolveTie(hands[i], hands[j])
                } else {
                    hands[j].rank++
                }
            }
        }
    }

    private fun resolveTie(firstHand: Hand, secondHand: Hand) {
        var cardIndex = 0
        while (cardIndex < firstHand.cards.size) {
            if (firstHand.cards[cardIndex].value > secondHand.cards[cardIndex].value) {
                firstHand.rank++
                break
            } else if (firstHand.cards[cardIndex].value < secondHand.cards[cardIndex].value) {
                secondHand.rank++
                break
            }
            cardIndex++
        }
    }

    private fun mostCommonCard(cards: List<Card>): Card {
        if (cards.all { it.name == 'J' }) {
            return cards.first()
        }

        var mostCommonCard = cards.first { it.name != 'J' }
        for (card in cards.filter { it.name != 'J' }.distinctBy { it.value }) {
            if (cards.count { it.value == card.value } > cards.count { it.value == mostCommonCard.value }) {
                mostCommonCard = card
            }
        }
        return mostCommonCard
    }

    private fun calculateStrengthQ1(cards: List<Card>): Int {
        var strenght = 0
        for (card in cards.distinctBy { it.value }) {
            strenght += when (cards.count { it.value == card.value }) {
                2 -> 1
                3 -> 3
                4 -> 5
                5 -> 9
                else -> 0
            }
        }
        return strenght
    }

    private fun calculateStrengthQ2(cards: List<Card>): Int {
        var strenght = 0
        val mostCommonCard = mostCommonCard(cards)

        val tempCards = cards.map { if (it.name == 'J') mostCommonCard else it }

        for (card in tempCards.distinctBy { it.value }) {
            strenght += when (tempCards.count { it.value == card.value }) {
                2 -> 1
                3 -> 3
                4 -> 5
                5 -> 9
                else -> 0
            }
        }

        return strenght
    }

    class Hand(
        val bid: Int, val cards: List<Card>
    ) {
        var rank = 1
        var strength = 0
    }

    class Card(
        var name: Char,
    ) {
        var value = when (name) {
            'A' -> 14
            'K' -> 13
            'Q' -> 12
            'J' -> 11
            'T' -> 10
            else -> {
                name.digitToInt()
            }
        }
    }
}