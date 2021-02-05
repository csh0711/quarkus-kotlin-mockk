package info.novatec

import io.quarkus.runtime.Quarkus
import javax.ws.rs.core.Application

/**
 * Exemplary Quarkus application.
 *
 * @author Christian Schwörer
 * @see <a href="https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk">Novatec Blog Post</a>
 */
class MyApplication : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Quarkus.run(*args)
        }
    }
}
