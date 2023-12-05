package adventofcode_2023

import AbstractDay

class Day5 : AbstractDay(5) {
    override fun question1(): Any {

        val seeds = input[0].substringAfter(": ").split(" ").map { it.toLong() }

        val seedToSoilMap = mutableMapOf<Long, Long>()
        val soilToFertilizerMap = mutableMapOf<Long, Long>()
        val fertilizerToWaterMap = mutableMapOf<Long, Long>()
        val waterToLightMap = mutableMapOf<Long, Long>()
        val lightToTemperatureMap = mutableMapOf<Long, Long>()
        val temperatureToHumidityMap = mutableMapOf<Long, Long>()
        val humidityToLocationMap = mutableMapOf<Long, Long>()

        var index = 0
        while (index < input.size) {
            if (input[index] == "seed-to-soil map:") {
                index++
                while (input[index] != "") {
                    val initialSoil = input[index].split(" ")[0].toLong()
                    val initialSeed = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    for (i in 0..<incrementAs) {
                        seedToSoilMap[initialSeed + i] = initialSoil + i
                    }

                    index++
                }
            }

            if (input[index] == "soil-to-fertilizer map:") {
                index++
                while (input[index] != "") {
                    val initialFertilizer = input[index].split(" ")[0].toLong()
                    val initialSoil = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    for (i in 0..<incrementAs) {
                        soilToFertilizerMap[initialSoil + i] = initialFertilizer + i
                    }

                    index++
                }
            }

            if (input[index] == "fertilizer-to-water map:") {
                index++
                while (input[index] != "") {
                    val initialWater = input[index].split(" ")[0].toLong()
                    val initialFertilizer = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    for (i in 0..<incrementAs) {
                        fertilizerToWaterMap[initialFertilizer + i] = initialWater + i
                    }

                    index++
                }
            }

            if (input[index] == "water-to-light map:") {
                index++
                while (input[index] != "") {
                    val initialLight = input[index].split(" ")[0].toLong()
                    val initialWater = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    for (i in 0..<incrementAs) {
                        waterToLightMap[initialWater + i] = initialLight + i
                    }

                    index++
                }
            }

            if (input[index] == "light-to-temperature map:") {
                index++
                while (input[index] != "") {
                    val initialTemperature = input[index].split(" ")[0].toLong()
                    val initialLight = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    for (i in 0..<incrementAs) {
                        lightToTemperatureMap[initialLight + i] = initialTemperature + i
                    }

                    index++
                }
            }

            if (input[index] == "temperature-to-humidity map:") {
                index++
                while (input[index] != "") {
                    val initialHumidity = input[index].split(" ")[0].toLong()
                    val initialTemperature = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    for (i in 0..<incrementAs) {
                        temperatureToHumidityMap[initialTemperature + i] = initialHumidity + i
                    }

                    index++
                }
            }

            if (input[index] == "humidity-to-location map:") {
                index++

                while (input[index] != "") {
                    val initialLocation = input[index].split(" ")[0].toLong()
                    val initialHumidity = input[index].split(" ")[1].toLong()
                    val incrementAs = input[index].split(" ")[2].toLong()

                    for (i in 0..<incrementAs) {
                        humidityToLocationMap[initialHumidity + i] = initialLocation + i
                    }

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
            var soil = seedToSoilMap[seed]

            if (soil == null) {
                seedToSoilMap[seed] = seed
                soil = seed
            }

            var fertilizer = soilToFertilizerMap[soil]
            if (fertilizer == null) {
                soilToFertilizerMap[soil] = soil
                fertilizer = soil
            }

            var water = fertilizerToWaterMap[fertilizer]
            if (water == null) {
                fertilizerToWaterMap[fertilizer] = fertilizer
                water = fertilizer
            }

            var light = waterToLightMap[water]
            if (light == null) {
                waterToLightMap[water] = water
                light = water
            }

            var temperature = lightToTemperatureMap[light]
            if (temperature == null) {
                lightToTemperatureMap[light] = light
                temperature = light
            }

            var humidity = temperatureToHumidityMap[temperature]
            if (humidity == null) {
                temperatureToHumidityMap[temperature] = temperature
                humidity = temperature
            }

            var location = humidityToLocationMap[humidity]
            if (location == null) {
                humidityToLocationMap[humidity] = humidity
                location = humidity
            }

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