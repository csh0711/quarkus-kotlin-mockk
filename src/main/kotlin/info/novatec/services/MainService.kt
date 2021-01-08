package info.novatec.services

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MainService(
    private val subService: SubService
) {
    fun sayHello() = "Hello from the real MainService - ${subService.sayHello()}"
}
