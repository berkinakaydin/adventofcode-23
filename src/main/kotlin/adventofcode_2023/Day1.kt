package adventofcode_2023

import AbstractDay

class Day1: AbstractDay(1) {

    enum class Digit(val value: Int) {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9)
    }

    override fun question1(): Int {
        var output = 0

        input.forEach { line ->
            val firstDigit = line.first { it.isDigit() }.digitToInt()
            val secondDigit = line.last { it.isDigit() }.digitToInt()

            output += 10 * firstDigit + secondDigit
        }

        return output
    }

    override fun question2(): Int {
        var output = 0

        input.forEach { line ->
            var firstDigit: Int? = null
            var secondDigit: Int? = null

            val digitAsStringIndexMap = mutableMapOf<Int, Int>()

            Digit.entries.forEach { digit ->
                val indices =
                    Regex(digit.toString(), RegexOption.IGNORE_CASE).findAll(line).map { it.range.first }.toList()

                indices.forEach {
                    digitAsStringIndexMap[it] = digit.value
                }
            }

            val digitAsIntIndexMap = line.mapIndexed { index, c ->
                if (c.isDigit()) {
                    index to c.digitToInt()
                } else {
                    null
                }
            }.filterNotNull().toMap()

            (digitAsStringIndexMap + digitAsIntIndexMap).toSortedMap().forEach {
                if (firstDigit == null) {
                    firstDigit = it.value
                } else {
                    secondDigit = it.value
                }
            }

            if (secondDigit == null) {
                secondDigit = firstDigit
            }

            output += "$firstDigit$secondDigit".toInt()
        }

        return output
    }
}