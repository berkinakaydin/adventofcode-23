package adventofcode_2023

import AbstractDay

class Day6 : AbstractDay(6) {
    override fun question1(): Any {
        val times = input[0].replace(Regex("[^0-9 ]"), "").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
        val distances = input[1].replace(Regex("[^0-9 ]"), "").split(" ").filter { it.isNotBlank() }.map { it.toLong() }

        var output = 1;

        times.forEachIndexed { raceIndex, time ->
            output *= findWinningRounds(time, distances[raceIndex])
        }
        return output
    }

    override fun question2(): Any {
        val time = input[0].replace(Regex("[^0-9]"), "").toLong()
        val distance = input[1].replace(Regex("[^0-9]"), "").toLong()
        return findWinningRounds(time, distance)
    }

    private fun findWinningRounds(time: Long, distance: Long): Int {
        var winningRounds = 0

        for (i in 0..time / 2) {
            if (distance < i * (time - i)) {
                winningRounds++
            }
        }

        return if(time % 2 == 0L){
            winningRounds * 2 - 1
        } else{
            winningRounds * 2
        }
    }
}