package com.mehrbod.routing

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class InputRequest(
    val url: String
)

@Serializable
data class Response(
    val shortUrl: String
)

fun Route.creationRouting() {
    route("/create") {
        post("/api/url") {
            val incomingData = call.receive<InputRequest>()
            memory[incomingData.url.getShortUrl()] = incomingData.url
            call.respond(Response(incomingData.url.getShortUrl()))
        }
    }
}

fun String.getShortUrl(): String {
    return this.encodeToID()
}