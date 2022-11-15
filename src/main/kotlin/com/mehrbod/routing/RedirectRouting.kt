package com.mehrbod.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.math.BigInteger
import java.security.MessageDigest

fun Route.redirectRoute() {
    get("/{link}") {
        val shortUrl = call.parameters["link"]
        call.respondRedirect(dao.originalUrl(shortUrl!!)?.originalUrl!!)
    }
}
