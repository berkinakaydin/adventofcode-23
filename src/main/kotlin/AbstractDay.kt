abstract class AbstractDay(day: Int) {
    var input: List<String> = listOf()

    init {
        input = FileReader().readFileAsLinesUsingBufferedReader("src/main/resources/d${day}/input.txt")
    }

    abstract fun question1(): Any
    abstract fun question2(): Any
}