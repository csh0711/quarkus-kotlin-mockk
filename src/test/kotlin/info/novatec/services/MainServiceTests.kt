package info.novatec.services

import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.junit.QuarkusMock
import io.quarkus.test.junit.QuarkusTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import javax.enterprise.inject.Default
import javax.inject.Inject

@QuarkusTest
class MainServiceTests {

    companion object {
        val subService: SubService = mockk()
        @BeforeAll
        @JvmStatic
        fun setupMocks() {
            QuarkusMock.installMockForType(subService, SubService::class.java)
        }
    }

    // Old approach using @Mock which might affect other test classes (see https://quarkus.io/blog/mocking/)
    // @Mock
    // fun subServiceA(): SubService = subService

    @Inject
    @field: Default
    lateinit var testee: MainService

    @Test
    fun `run test with mocked sub services`() {
        every { subService.sayHello() } returns "SubService A mocked"

        val result = testee.sayHello()

        assertThat(result).isEqualTo("Hello from the real MainService - SubService A mocked")
    }

}