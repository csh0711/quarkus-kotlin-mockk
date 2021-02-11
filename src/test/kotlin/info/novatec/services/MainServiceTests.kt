package info.novatec.services

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * This test class shows how to test a Quarkus CDI bean with JUnit and MockK in a unit test.
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk">Novatec Blog Post</a>
 */
class MainServiceTests {

    private val subService: SubService = mockk()

    private val testee = MainService(subService)

    @Test
    fun `call sayHello() with mocked sub service`() {
        every { subService.sayHello() } returns "Hello from MOCKED SubService"

        val result = testee.sayHello()

        assertThat(result).isEqualTo("Hello from the REAL MainService - Hello from MOCKED SubService")
    }
}
