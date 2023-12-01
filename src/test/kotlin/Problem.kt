import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Problem {
    private var input: List<String> = listOf()

    init {
        input = FileReader().readFileAsLinesUsingBufferedReader("src/test/resources/input.txt")
        //input = FileReader().readFileAsLinesUsingBufferedReader("src/test/resources/small-input.txt")
    }

    @Test
    fun question1() {
        var output = 0

        input.forEach { line ->
            val firstDigit = line.first{ it.isDigit() }.digitToInt()
            val secondDigit = line.last{ it.isDigit() }.digitToInt()

            output += 10 * firstDigit + secondDigit
        }

        assertEquals(56049, output)
    }

    @Test
    fun question2() {
        var output = 0

        input.forEach { line ->
            var firstDigit: Int? = null
            var secondDigit: Int? = null

            val digitAsStringIndexMap = mutableMapOf<Int, Int>()

            Digit.entries.forEach { digit ->
                val indices =
                    Regex(digit.toString(), RegexOption.IGNORE_CASE).findAll(line).map { it.range.first }.toList()

                indices.forEach {
                    digitAsStringIndexMap[it] = digit.value
                }
            }

            val digitAsIntIndexMap = line.mapIndexed { index, c ->
                if (c.isDigit()) {
                    index to c.digitToInt()
                } else {
                    null
                }
            }.filterNotNull().toMap()

            (digitAsStringIndexMap + digitAsIntIndexMap).toSortedMap().forEach {
                if (firstDigit == null) {
                    firstDigit = it.value
                } else {
                    secondDigit = it.value
                }
            }

            if (secondDigit == null) {
                secondDigit = firstDigit
            }

            output += "$firstDigit$secondDigit".toInt()
        }

        assertEquals(54530, output)
    }

    enum class Digit(val value: Int) {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9)
    }


    private var start: Long = 0L

    @BeforeEach
    fun beforeEach() {
        start = System.currentTimeMillis();
    }

    @AfterEach
    fun afterEach() {
        val end = System.currentTimeMillis();
        println("Time taken: ${end - start}ms")
    }
}