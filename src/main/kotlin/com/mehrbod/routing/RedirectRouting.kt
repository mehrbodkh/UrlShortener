package com.mehrbod.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.redirectRoute() {
    get("/{link}") {
        val shortUrl = call.parameters["link"]
        println(shortUrl)
        call.respondRedirect("https://www.google.com")
    }
}