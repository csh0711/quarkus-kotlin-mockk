package info.novatec.services

import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.junit.QuarkusMock
import io.quarkus.test.junit.QuarkusTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.enterprise.inject.Default
import javax.inject.Inject

/**
 * This test class shows how to test a Quarkus CDI bean with JUnit and MockK.
 *
 * In this example the `@BeforeEach` annotation is used instead of `@BeforeAll`.
 * For other possible implementations see [MainServiceTestsWithCompanionObject]
 * and [MainServiceTestsWithTestPerClass].
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/">Associated Blog Post</a>
 */
@QuarkusTest
class MainServiceTestsWithTestPerClass {

    private val subService: SubService = mockk()

    @BeforeEach
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
