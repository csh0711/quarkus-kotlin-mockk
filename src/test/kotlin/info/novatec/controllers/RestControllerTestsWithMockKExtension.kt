package info.novatec.controllers

import info.novatec.JsonMatcher
import info.novatec.services.MainService
import io.mockk.every
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import javax.ws.rs.core.MediaType

/**
 * This test class shows how to test a Quarkus RestController with JUnit and MockK a QuarkusTest.
 *
 * This example uses the [Quarkus JUnit5 MockK Extension](https://github.com/quarkiverse/quarkus-mockk).
 * For other possible implementations see [RestControllerTestsWithCompanionObject],
 * [MainServiceIntTestsWithTestPerClass] and [RestControllerTestsWithBeforeEach].
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk">Novatec Blog Post</a>
 */
@QuarkusTest
class RestControllerTestsWithMockKExtension {

    @InjectMock
    private lateinit var mainService: MainService

    @Test
    fun `GET hello - with mocked main service`() {
        every { mainService.sayHello() } returns "Hello from MOCKED main service"

        val expectedJson = """{ "greeting": "Hello from MOCKED main service" }"""

        given()
            .`when`()["/hello"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedJson))

    }

    @Test
    fun `GET hello - exception is mapped to status 500`() {
        every { mainService.sayHello() } throws RuntimeException()

        given()
            .`when`()["/hello"]
            .then()
            .statusCode(500)
    }
}
