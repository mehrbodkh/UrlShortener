package com.mehrbod.routing

import com.mehrbod.data.UrlRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.redirectRoute() {
    val urlRepository: UrlRepository by inject()
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
