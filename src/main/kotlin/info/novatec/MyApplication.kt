package info.novatec

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.annotations.QuarkusMain
import javax.ws.rs.core.Application

class MyApplication : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Quarkus.run(*args)
        }
    }
}
