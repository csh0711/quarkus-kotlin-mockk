package info.novatec.services

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SubService {
    fun sayHello() = "Hello from the real SubService"
}
