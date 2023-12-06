package day02

import println
import readInput

data class Cube(val amount: Int, val color: String)
data class CubeSet(val cubes: List<Cube>)
data class Game(val id: Int, val cubeSets: List<CubeSet>)

fun String.toCube(): Cube {
    val (amount, color) = this
        .trim()
        .split(" ")
    return Cube(amount.toInt(), color)
}

fun String.toCubeSet(): CubeSet {
    val cubes = this
        .split(",")
        .map { cubeString -> cubeString.toCube() }
    return CubeSet(cubes)
}

fun String.toCubeSets(): List<CubeSet> {
    return this
        .split(";")
        .map { it.toCubeSet() }
}

fun String.toGame(): Game {
    val (gameId, gameInfo) = this.split(":")
    val (_, id) = gameId.split((" "))
    val sets = gameInfo.toCubeSets()

    return Game(id.toInt(), sets)
}

fun List<Cube>.sumOfColor(color: String): Int {
    return this
        .filter { cube -> cube.color == color }
        .sumOf { cube -> cube.amount }
}

fun List<Cube>.maxCubesOfEachColor(): List<Cube> {
    return this
        .groupBy { it.color }
        .map { it.value.maxBy { c -> c.amount } }
}

fun List<CubeSet>.sumOfMaxOfColors(): Int {
    return this
        .flatMap { it.cubes }
        .maxCubesOfEachColor()
        .map { it.amount }
        .reduce { acc, amount -> acc * amount }
}

fun List<CubeSet>.isValid(): Boolean {
    return this.all {
        it.cubes.sumOfColor("red") <= 12 &&
        it.cubes.sumOfColor("green") <= 13 &&
        it.cubes.sumOfColor("blue") <= 14
    }
}

fun main() {
    val DAY = "day02"

    fun part1(input: List<String>): Int {
        return input
            .map { it.toGame() }
            .filter { it.cubeSets.isValid() }
            .sumOf { it.id }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toGame() }
            .sumOf { it.cubeSets.sumOfMaxOfColors() }
    }

    // test if implementation meets criteria from the description, like:
    println("Part One")
    val testInput = readInput("${DAY}/part1_test")
    check(part1(testInput) == 8)

    val input = readInput("${DAY}/input")
    part1(input).println()

    println("Part Two")
    val testInputP2 = readInput("${DAY}/part2_test")
    check(part2(testInputP2) == 2286)

    part2(input).println()
}