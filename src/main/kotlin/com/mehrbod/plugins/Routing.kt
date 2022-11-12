package com.mehrbod.plugins

import com.mehrbod.routing.creationRouting
import com.mehrbod.routing.redirectRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        creationRouting()
        redirectRoute()
    }
}
