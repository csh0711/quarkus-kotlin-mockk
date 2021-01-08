package info.novatec.controller

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

@QuarkusTest
@TestInstance(PER_CLASS)
class RestControllerTests {

    @BeforeAll
    fun setupMocks() {
        QuarkusMock.installMockForType(mainService, MainService::class.java)
    }

    @Test
    fun `GET - hello`() {
        every { mainService.sayHello() } returns "Hello from mocked main service"

        val expectedJson = """{ "greeting": "Hello from mocked main service" }"""

        given()
            .`when`()["/hello"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedJson))

    }

}