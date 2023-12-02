import adventofcode_2023.Day2
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {
    private val day = Day2()

//    init {
//        val input = FileReader().readFileAsLinesUsingBufferedReader("src/test/resources/small-input.txt")
//        day.input = input
//    }

    @Test
    fun question1() {
        assertEquals(56049, day.question1())
    }

    @Test
    fun question2() {
        assertEquals(54530, day.question2())
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