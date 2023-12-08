package adventofcode_2023

import AbstractDay

class Day8 : AbstractDay(8) {
    override fun question1(): Any {
        val directions = input[0].toCharArray()
        val nodes = parseInput()
        var position = nodes.first { it.name == "AAA" }

        var numberOfIteration = 0
        while (position.name != "ZZZ"){
            directions.forEach { direction ->
                when(direction){
                    'L' -> position = position.left
                    'R' -> position = position.right
                }
                numberOfIteration++
            }
        }

       return numberOfIteration
    }

    override fun question2(): Any {
        return 0
    }

    private fun parseInput():List<Node>{
        val nodes = mutableListOf<Node>()
        for (i in 2..<input.size){
            val line = input[i]
            val nodeName = line.substring(0, 3)
            nodes.add(Node(nodeName))
        }

        for (i in 2..<input.size){
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

    class Node(val name:String){
        lateinit var right:Node
        lateinit var left:Node
    }
}