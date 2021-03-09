package info.novatec.services


import io.mockk.every
import io.mockk.verify
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.TestTransaction
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import javax.inject.Inject

/**
 * This test class shows how to test a Quarkus CDI bean with JUnit and MockK in a QuarkusTest.
 *
 * This example uses the [Quarkus JUnit5 MockK Extension](https://github.com/quarkiverse/quarkus-mockk).
 * For other possible implementations see [MainServiceTestsWithBeforeEach],
 * [MainServiceIntTestsWithTestPerClass] and [MainServiceIntTestsWithCompanionObject].
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk">Novatec Blog Post</a>
 */
@QuarkusTest
@TestTransaction
@TestProfile(MainServiceIntTestsWithMockKExtensionProfile::class) // Only needed as MainServiceTest exists several times
class MainServiceIntTestsWithMockKExtension {

    @Inject
    private lateinit var testee: MainService

    @InjectMock
    private lateinit var subService: SubService

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
class MainServiceIntTestsWithMockKExtensionProfile : QuarkusTestProfile
