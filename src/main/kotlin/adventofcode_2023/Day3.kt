package adventofcode_2023

import AbstractDay

class Day3 : AbstractDay(3) {
    override fun question1(): Any {
        val map = parseMap()

        var result = 0

        map.filter { mapItem -> mapItem.value.all { it.isDigit() } }.forEach {
            if (lookVertical(map, it) || lookHorizontal(map, it)) {
                result += it.value.toInt()
            }
        }

        return result
    }


    override fun question2(): Any {
        TODO("Not yet implemented")
    }

    private fun lookVertical(map: List<MapItem>, item: MapItem): Boolean {
        val vertical = map.filter { it.rowIndex == item.rowIndex + 1 || it.rowIndex == item.rowIndex - 1 }
        return vertical.any { it.firstColIndex >= item.firstColIndex - 1 && it.lastColIndex <= item.lastColIndex + 1 }
    }

    private fun lookHorizontal(map: List<MapItem>, item: MapItem): Boolean {
        val horizontal = map.filter { it.rowIndex == item.rowIndex }
        return horizontal.any { it.firstColIndex == item.firstColIndex - 1 || it.lastColIndex == item.lastColIndex + 1 }
    }

    private fun parseMap(): List<MapItem> {
        val map = mutableListOf<MapItem>()
        input.forEachIndexed { rowIndex, line ->
            var colIndex = 0

            while (colIndex < line.length) {
                var foundDigit = ""
                val foundIndex = colIndex

                if (line[colIndex].isDigit()) {
                    while (line[colIndex].isDigit()) {
                        foundDigit += line[colIndex]
                        colIndex++
                        if (colIndex == line.length) {
                            break
                        }
                    }

                    map.add(MapItem(foundDigit, foundIndex, --colIndex, rowIndex))
                } else if (line[colIndex] != '.') {
                    map.add(MapItem(line[colIndex].toString(), foundIndex, colIndex, rowIndex))
                }

                colIndex++
            }
        }
        return map
    }

    class MapItem(
        val value: String, val firstColIndex: Int, val lastColIndex: Int, val rowIndex: Int
    )
}