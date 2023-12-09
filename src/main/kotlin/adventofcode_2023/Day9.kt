package adventofcode_2023

import AbstractDay

class Day9 : AbstractDay() {
    override fun question1(): Any {
        val histories = input.map { line -> line.split(" ").map { it.toInt() } }

        var result = 0

        histories.forEach { history ->
            result += processHistory(history)
        }

        return result
    }

    override fun question2(): Any {
        val histories = input.map { line -> line.split(" ").map { it.toInt() } }
        var result = 0

        histories.forEach { history ->
            result += processHistory(history.reversed())
        }

        return result
    }

    private fun processHistory(history: List<Int>): Int {
        val tempHistory: MutableList<List<Int>> = mutableListOf(history)
        var historyIndex = 0

        while (!tempHistory.last().all { it == 0 }) {
            val extrapolate = mutableListOf<Int>()
            for (i in 0..<(tempHistory[historyIndex].size - 1)) {
                val diff = tempHistory[historyIndex][i + 1] - tempHistory[historyIndex][i]
                extrapolate.add(diff)
            }
            tempHistory.addAll(listOf(extrapolate))
            historyIndex++
        }

        for (i in tempHistory.size - 1 downTo 1) {
            tempHistory[i - 1].addLast(tempHistory[i].last() + tempHistory[i - 1].last())
        }

        return tempHistory[0].last()
    }
}