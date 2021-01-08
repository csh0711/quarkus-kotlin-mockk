package info.novatec.controller

import info.novatec.services.MainService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response


@Path("/")
class RestController(
    private val mainService: MainService
) {

    @GET
    @Path("/hello")
    @Produces(APPLICATION_JSON)
    fun greeting() = mainService.sayHello().let {
        Response.ok(GreetingResponse(it)).build()
    }
}

data class GreetingResponse(val greeting: String)
