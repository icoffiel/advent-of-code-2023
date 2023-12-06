package day01

import println
import readInput

fun main() {
    val DAY = "day01"

    fun part1(input: List<String>): Int {
        return input
            .map { it.replace("""\D""".toRegex(), "") }
            .map { it.first().toString() + it.last().toString() }
            .sumOf { it.toInt() }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toStringDigits() }
            .map { it.first().toString() + it.last().toString() }
            .sumOf { it.toInt() }
    }

    // test if implementation meets criteria from the description, like:
    println("Part One")
    val testInput = readInput("${DAY}/part1_test")
    check(part1(testInput) == 142)

    val input = readInput("${DAY}/input")
    part1(input).println()

    println("Part Two")
    val testInputP2 = readInput("${DAY}/part2_test")
    check(part2(testInputP2) == 281)

    part2(input).println()
}

private fun String.toStringDigits(): String {
    return this
        .windowed(this.length, partialWindows = true)
        .joinToString("") { it.toStringDigit() }
}

private fun String.toStringDigit(): String {
    if (this.first().isDigit()) {
        return this.first().toString()
    }

    return when {
        this.startsWith("one") -> "1"
        this.startsWith("two") -> "2"
        this.startsWith("three") -> "3"
        this.startsWith("four") -> "4"
        this.startsWith("five") -> "5"
        this.startsWith("six") -> "6"
        this.startsWith("seven") -> "7"
        this.startsWith("eight") -> "8"
        this.startsWith("nine") -> "9"
        else -> ""
    }
}
