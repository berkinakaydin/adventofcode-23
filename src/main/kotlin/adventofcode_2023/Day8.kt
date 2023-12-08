package adventofcode_2023

import AbstractDay

class Day8 : AbstractDay(8) {
    override fun question1(): Any {
        val directions = input[0].toCharArray()
        val nodes = parseInput()
        var position = nodes.first { it.name == "AAA" }

        var numberOfIteration = 0
        while (position.name != "ZZZ") {
            directions.forEach { direction ->
                when (direction) {
                    'L' -> position = position.left
                    'R' -> position = position.right
                }
                numberOfIteration++
            }
        }

        return numberOfIteration
    }

    override fun question2(): Any {
        val directions = input[0].toCharArray()
        val nodes = parseInput()
        val positions = nodes.filter { it.name.endsWith('A') }.toMutableList()

        val numberOfIterationsList = mutableListOf<Int>()
        positions.forEach {
            var numberOfIteration = 0
            var position = it

            while (!position.name.endsWith("Z")) {
                for (i in directions.indices) {
                    when (directions[i]) {
                        'L' -> position = position.left
                        'R' -> position = position.right
                    }
                    numberOfIteration++
                    if (position.name.endsWith("Z")) {
                        break
                    }
                }
            }
            numberOfIterationsList.add(numberOfIteration)
        }

        return findLCM(numberOfIterationsList.map { it.toLong() }.toLongArray())
    }

    private fun findLCM(numbers: LongArray): Long {
        // Function to find the LCM of two numbers
        fun findLCMOfTwo(a: Long, b: Long): Long {
            // Find the greatest common divisor (GCD) using Euclidean algorithm
            fun findGCD(x: Long, y: Long): Long = if (y.toInt() == 0) x else findGCD(y, x % y)

            // LCM = (a * b) / GCD(a, b)
            val gcd = findGCD(a, b)
            return (a * b) / gcd
        }

        // Check if the array is not empty
        require(numbers.isNotEmpty()) { "Array must not be empty" }

        // Initialize lcm with the first number
        var lcm = numbers[0].toLong()

        // Iterate through the remaining numbers and find the LCM
        for (i in 1..<numbers.size) {
            lcm = findLCMOfTwo(lcm, numbers[i])
        }

        return lcm
    }

    private fun parseInput(): List<Node> {
        val nodes = mutableListOf<Node>()
        for (i in 2..<input.size) {
            val line = input[i]
            val nodeName = line.substring(0, 3)
            nodes.add(Node(nodeName))
        }

        for (i in 2..<input.size) {
            val line = input[i]
            val nodeName = line.substring(0, 3)
            val neighbours = line.substringAfter("(").substringBefore(")").split(", ")

            val node = nodes.first { it.name == nodeName }
            val leftNode = nodes.first { it.name == neighbours[0] }
            val rightNode = nodes.first { it.name == neighbours[1] }
            node.left = leftNode
            node.right = rightNode
        }

        return nodes
    }

    class Node(val name: String) {
        lateinit var right: Node
        lateinit var left: Node
    }
}