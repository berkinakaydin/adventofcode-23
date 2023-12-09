package adventofcode_2023

import AbstractDay

class Day2 : AbstractDay() {
    override fun question1(): Any {
        val targetBlue = 14
        val targetRed = 12
        val targetGreen = 13

        var output = 0

        input.forEach { line ->
            val game = parseInput(line)

            if (game.subsets.all {
                    it.blue <= targetBlue && it.red <= targetRed && it.green <= targetGreen
                }) {
                output += game.id
            }
        }
        return output
    }

    override fun question2(): Any {
        var output = 0

        input.forEach { line ->
            val game = parseInput(line)

            var minBlue = Integer.MIN_VALUE
            var minGreen = Integer.MIN_VALUE
            var minRed = Integer.MIN_VALUE

            game.subsets.forEach() {
                if (it.blue >= minBlue) {
                    minBlue = it.blue
                }
                if (it.red >= minRed) {
                    minRed = it.red
                }
                if (it.green >= minGreen) {
                    minGreen = it.green
                }
            }

            output += minBlue * minRed * minGreen
        }
        return output
    }

    private fun parseInput(input: String): Game {
        val gameId = input.split(":")[0].split(" ")[1].toInt()

        val gameSections = input.split(":")[1].split(";")

        var blueCount = 0
        var redCount = 0
        var greenCount = 0

        val subsets = mutableListOf<Subset>()

        for (section in gameSections) {
            val counts = section.split(",").map { it.trim() }

            for (countString in counts) {
                val count = countString.split(" ")[0].toInt()
                val color = countString.split(" ")[1]

                when (color) {
                    "blue" -> blueCount = count
                    "red" -> redCount = count
                    "green" -> greenCount = count
                }
            }

            subsets.add(Subset(blueCount, redCount, greenCount))
        }

        return Game(gameId, subsets)
    }

    private class Subset(
        val blue: Int, val red: Int, val green: Int
    )

    private class Game(
        val id: Int, val subsets: List<Subset>
    )
}