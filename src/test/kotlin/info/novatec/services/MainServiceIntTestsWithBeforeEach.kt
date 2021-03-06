package info.novatec.services

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.quarkus.test.junit.QuarkusMock
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

/**
 * This test class shows how to test a Quarkus CDI bean with JUnit and MockK in a QuarkusTest.
 *
 * In this example the `@BeforeEach` annotation is used instead of `@BeforeAll`.
 * For other possible implementations see [MainServiceIntTestsWithCompanionObject],
 * [MainServiceIntTestsWithMockKExtension] and [MainServiceTestsWithTestPerClass].
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk">Novatec Blog Post</a>
 */
@QuarkusTest
@TestProfile(MainServiceIntTestsWithBeforeEachProfile::class) // Only needed as MainServiceTests exists several times
class MainServiceIntTestsWithBeforeEach {

    private val subService: SubService = mockk()

    @BeforeEach
    fun setupMocks() {
        QuarkusMock.installMockForType(subService, SubService::class.java)
    }

    @Inject
    lateinit var testee: MainService

    @Test
    fun `call sayHello with mocked sub service and make sure caching works`() {
        every { subService.sayHello() } returns "Hello from MOCKED SubService"

        val firstCallResult = testee.sayHello()
        val secondCallResult = testee.sayHello()

        assertThat(firstCallResult).isEqualTo("Hello from the REAL MainService - Hello from MOCKED SubService")
        assertThat(secondCallResult).isEqualTo("Hello from the REAL MainService - Hello from MOCKED SubService")
        verify(exactly = 1) { subService.sayHello() }
    }
}

/**
 * Only needed as for demo purposes `MainServiceTests` exists several times.
 *
 * [QuarkusTest] shares the profile between tests if none is specified.
 * By defining an own profile for a test class Quarkus is stopped, and then
 * re-started with the new config.
 *
 * @see <a href="https://quarkus.io/blog/quarkus-test-profiles/">Quarkus Blog</a>
 */
class MainServiceIntTestsWithBeforeEachProfile : QuarkusTestProfile
