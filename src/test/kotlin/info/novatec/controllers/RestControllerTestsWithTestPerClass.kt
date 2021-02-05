package info.novatec.controllers

import info.novatec.JsonMatcher
import info.novatec.services.MainService
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.junit.QuarkusMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import javax.ws.rs.core.MediaType

val mainService: MainService = mockk()

/**
 * This test class shows how to test a Quarkus RestController with JUnit and MockK.
 *
 * In this example the `@TestInstance(PER_CLASS)` annotation is used.
 * For other possible implementations see [RestControllerTestsWithCompanionObject]
 * and [RestControllerTestsWithBeforeEach].
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk">Novatec Blog Post</a>
 */
@QuarkusTest
@TestInstance(PER_CLASS)
class RestControllerTestsWithTestPerClass {

    @BeforeAll
    fun setupMocks() {
        QuarkusMock.installMockForType(mainService, MainService::class.java)
    }

    @Test
    fun `GET - hello (with mocked main service)`() {
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
    fun `GET - exception is mapped to status 500`() {
        every { mainService.sayHello() } throws RuntimeException()

        given()
            .`when`()["/hello"]
            .then()
            .statusCode(500)
    }
}
