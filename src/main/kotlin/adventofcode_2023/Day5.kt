package adventofcode_2023

import AbstractDay

class Day5 : AbstractDay(5) {
    override fun question1(): Any {

        val seeds = input[0].substringAfter(": ").split(" ").map { it.toLong() }

        val seedToSoilMap = mutableMapOf<Pair<Long, Long>, Long>()
        val soilToFertilizerMap = mutableMapOf<Pair<Long, Long>, Long>()
        val fertilizerToWaterMap = mutableMapOf<Pair<Long, Long>, Long>()
        val waterToLightMap = mutableMapOf<Pair<Long, Long>, Long>()
        val lightToTemperatureMap = mutableMapOf<Pair<Long, Long>, Long>()
        val temperatureToHumidityMap = mutableMapOf<Pair<Long, Long>, Long>()
        val humidityToLocationMap = mutableMapOf<Pair<Long, Long>, Long>()

        var index = 0
        while (index < input.size) {
            if (input[index] == "seed-to-soil map:") {
                index++
                while (input[index] != "") {
                    val initialSoil = input[index].split(" ")[0].toLong()
                    val initialSeed = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    seedToSoilMap[Pair(initialSeed, initialSeed + incrementAs)] = initialSoil - initialSeed

                    index++
                }
            }

            if (input[index] == "soil-to-fertilizer map:") {
                index++
                while (input[index] != "") {
                    val initialFertilizer = input[index].split(" ")[0].toLong()
                    val initialSoil = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    soilToFertilizerMap[Pair(initialSoil, initialSoil + incrementAs)] = initialFertilizer - initialSoil

                    index++
                }
            }

            if (input[index] == "fertilizer-to-water map:") {
                index++
                while (input[index] != "") {
                    val initialWater = input[index].split(" ")[0].toLong()
                    val initialFertilizer = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    fertilizerToWaterMap[Pair(initialFertilizer, initialFertilizer + incrementAs)] =
                        initialWater - initialFertilizer


                    index++
                }
            }

            if (input[index] == "water-to-light map:") {
                index++
                while (input[index] != "") {
                    val initialLight = input[index].split(" ")[0].toLong()
                    val initialWater = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    waterToLightMap[Pair(initialWater, initialWater + incrementAs)] = initialLight - initialWater

                    index++
                }
            }

            if (input[index] == "light-to-temperature map:") {
                index++
                while (input[index] != "") {
                    val initialTemperature = input[index].split(" ")[0].toLong()
                    val initialLight = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    lightToTemperatureMap[Pair(initialLight, initialLight + incrementAs)] =
                        initialTemperature - initialLight

                    index++
                }
            }

            if (input[index] == "temperature-to-humidity map:") {
                index++
                while (input[index] != "") {
                    val initialHumidity = input[index].split(" ")[0].toLong()
                    val initialTemperature = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    temperatureToHumidityMap[Pair(initialTemperature, initialTemperature + incrementAs)] =
                        initialHumidity - initialTemperature

                    index++
                }
            }

            if (input[index] == "humidity-to-location map:") {
                index++

                while (input[index] != "") {
                    val initialLocation = input[index].split(" ")[0].toLong()
                    val initialHumidity = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    humidityToLocationMap[Pair(initialHumidity, initialHumidity + incrementAs)] =
                        initialLocation - initialHumidity

                    index++

                    if (input.size == index) {
                        break
                    }
                }
            }

            index++
        }

        var minLocation = Long.MAX_VALUE

        seeds.forEach { seed ->
            val soil = seed + (seedToSoilMap.filterKeys { it.first <= seed && it.second >= seed }.values.firstOrNull() ?: 0)
            val fertilizer = soil + (soilToFertilizerMap.filterKeys { it.first <= soil && it.second >= soil }.values.firstOrNull() ?: 0)
            val water = fertilizer + (fertilizerToWaterMap.filterKeys { it.first <= fertilizer && it.second >= fertilizer }.values.firstOrNull() ?: 0)
            val light = water + (waterToLightMap.filterKeys { it.first <= water && it.second >= water }.values.firstOrNull() ?: 0)
            val temperature = light + (lightToTemperatureMap.filterKeys { it.first <= light && it.second >= light }.values.firstOrNull() ?: 0)
            val humidity = temperature + (temperatureToHumidityMap.filterKeys { it.first <= temperature && it.second >= temperature }.values.firstOrNull() ?: 0)
            val location = humidity + (humidityToLocationMap.filterKeys { it.first <= humidity && it.second >= humidity }.values.firstOrNull() ?: 0)

            if (location < minLocation) {
                minLocation = location
            }
        }

        return minLocation
    }

    override fun question2(): Any {
        TODO("Not yet implemented")
    }

}