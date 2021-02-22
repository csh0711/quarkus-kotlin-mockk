package info.novatec.services

import io.quarkus.cache.CacheResult
import javax.enterprise.context.ApplicationScoped

/**
 * Exemplary Quarkus CDI bean.
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk">Novatec Blog Post</a>
 */
@ApplicationScoped
class MainService(
    private val subService: SubService
) {
    @CacheResult(cacheName = "mainservice-sayhello-cache")
    fun sayHello() = "Hello from the REAL MainService - ${subService.sayHello()}"
}
