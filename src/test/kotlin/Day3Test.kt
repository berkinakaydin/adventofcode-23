import adventofcode_2023.Day3
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import kotlin.test.assertEquals

class Day3Test {
    private val day = Day3()
    private var start: Long = 0L

    init {
        val input = FileReader().readFileAsLinesUsingBufferedReader("src/test/resources/small-input.txt")
        day.input = input
    }

    @Test
    fun question1() {
        assertEquals(533775, day.question1())
    }

    @Test
    fun question2() {
        assertEquals(0, day.question2())
    }

    @BeforeEach
    fun beforeEach() {
        start = System.nanoTime()
    }

    @AfterEach
    fun afterEach(testInfo: TestInfo) {
        val end = System.nanoTime()
        println("${testInfo.testClass.get().name} ${testInfo.testMethod.get().name} - Time taken: ${(end - start).toDouble() / 1_000_000}ms")
    }
}