package com.mehrbod.routing

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

@kotlinx.serialization.Serializable
data class InputRequest(
    val url: String
)

@kotlinx.serialization.Serializable
data class Response(
    val shortUrl: String
)

fun Route.creationRouting() {
    route("/create") {
        post("/api/url") {
            val incomingData = call.receive<InputRequest>()
            println(incomingData)
            call.respond(Response(getShortUrl()))
        }
    }
}

fun getShortUrl(): String {
    return "https://www.google.com"
}