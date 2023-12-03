package adventofcode_2023

import AbstractDay

class Day3 : AbstractDay(3) {
    override fun question1(): Any {
        val map = parseMap()

        var result = 0

        map.filter { mapItem -> mapItem.value.all { it.isDigit() } }.forEach {
            if (findVerticalAdjecents(map, it).isNotEmpty() || findHorizontalAdjecents(map, it).isNotEmpty()) {
                result += it.value.toInt()
            }
        }

        return result
    }


    override fun question2(): Any {
        val map = parseMap()

        var result = 0

        val mapIterator = map.iterator()

        while (mapIterator.hasNext()) {
            val item = mapIterator.next()

            val verticalAdjecentGears = findVerticalAdjecents(map, item, "*")
            val horizontalAdjecentGears = findHorizontalAdjecents(map, item, "*")

            (verticalAdjecentGears + horizontalAdjecentGears).forEach { gear ->
                findVerticalAdjecents(map, gear).let { gearAdjescentItems ->
                    gearAdjescentItems.forEach { gearAdjescentItem ->
                        if (gearAdjescentItem != item) {
                            result += gearAdjescentItem.value.toInt() * item.value.toInt()
                            mapIterator.remove()
                        }
                    }
                }

                findHorizontalAdjecents(map, gear).let { gearAdjescentItems ->
                    gearAdjescentItems.forEach { gearAdjescentItem ->
                        if (gearAdjescentItem != item) {
                            result += gearAdjescentItem.value.toInt() * item.value.toInt()
                            mapIterator.remove()
                        }
                    }
                }
            }
        }
        return result
    }

    private fun findVerticalAdjecents(map: List<MapItem>, item: MapItem, target: String): List<MapItem> {
        val vertical = map.filter { it.rowIndex == item.rowIndex + 1 || it.rowIndex == item.rowIndex - 1 }
        return vertical.filter { (it.firstColIndex in item.firstColIndex - 1..item.lastColIndex + 1 || it.lastColIndex in item.firstColIndex - 1..item.lastColIndex + 1) && it.value == target }
    }

    private fun findVerticalAdjecents(map: List<MapItem>, item: MapItem): List<MapItem> {
        val vertical = map.filter { it.rowIndex == item.rowIndex + 1 || it.rowIndex == item.rowIndex - 1 }
        return vertical.filter { it.firstColIndex in item.firstColIndex - 1..item.lastColIndex + 1 || it.lastColIndex in item.firstColIndex - 1..item.lastColIndex + 1 }
    }

    private fun findHorizontalAdjecents(map: List<MapItem>, item: MapItem, target: String): List<MapItem> {
        val horizontal = map.filter { it.rowIndex == item.rowIndex }
        return horizontal.filter { (it.lastColIndex == item.firstColIndex - 1 || it.firstColIndex == item.lastColIndex + 1) && it.value == target }
    }

    private fun findHorizontalAdjecents(map: List<MapItem>, item: MapItem): List<MapItem> {
        val horizontal = map.filter { it.rowIndex == item.rowIndex }
        return horizontal.filter { it.lastColIndex == item.firstColIndex - 1 || it.firstColIndex == item.lastColIndex + 1 }
    }

    private fun parseMap(): MutableList<MapItem> {
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