package info.novatec.services

import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.junit.QuarkusMock
import io.quarkus.test.junit.QuarkusTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import javax.enterprise.inject.Default
import javax.inject.Inject

val subService: SubService = mockk()

/**
 * This test class shows how to test a Quarkus CDI bean with JUnit and MockK.
 *
 * In this example the `@TestInstance(PER_CLASS)` annotation is used.
 * For other possible implementations see [MainServiceTestsWithBeforeEach]
 * and [MainServiceTestsWithCompanionObject].
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk">Novatec Blog Post</a>
 */
@QuarkusTest
@TestInstance(PER_CLASS)
class MainServiceTestsWithBeforeEach {

    @BeforeAll
    fun setupMocks() {
        QuarkusMock.installMockForType(subService, SubService::class.java)
    }

    @Inject
    @field: Default
    lateinit var testee: MainService

    @Test
    fun `call sayHello() test with mocked sub service`() {
        every { subService.sayHello() } returns "Hello from MOCKED SubService"

        val result = testee.sayHello()

        assertThat(result).isEqualTo("Hello from the REAL MainService - Hello from MOCKED SubService")
    }
}
