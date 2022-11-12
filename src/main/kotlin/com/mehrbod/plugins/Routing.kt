package com.mehrbod.plugins

import com.mehrbod.routing.creationRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        creationRouting()
        testRoute()
        anotherRoute()
    }
}

fun Route.testRoute() {
    route("/test") {
        get {
            call.respondText("Hello World!")
        }
    }
}

fun Route.anotherRoute() {
    route("/test2") {
        get {
            call.respondText("Hello World!")
        }
    }
}