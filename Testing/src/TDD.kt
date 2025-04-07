class TDD<T>(
    private val name: String,
    private val result: T,
    private val expectedResult: T
) : Testing {
    override fun check() {
        if (result == expectedResult) {
            println("\u001B[32mSuccess: $name")
        } else {
            println("\u001b[31mFailure: $name (Expected $expectedResult but found $result)")
        }
    }
}

class TDDTest : Test