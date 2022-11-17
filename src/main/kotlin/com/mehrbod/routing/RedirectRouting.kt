package com.mehrbod.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.redirectRoute() {
    get("/{link}") {
        val shortUrl = call.parameters["link"]
        val originalUrl = shortUrl?.let { urlRepository.fetchUrl(shortUrl)?.originalUrl }
        originalUrl?.let {
            call.respondRedirect(originalUrl)
        } ?: run {
            call.respond("Not existent.")
        }
    }
}
