package com.mehrbod.routing

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

@kotlinx.serialization.Serializable
data class Test(
    val url: String
)

fun Route.creationRouting() {
    route("/create") {
        post("/api/url") {
            val incomingData = call.receive<Test>()
            println(incomingData)
            call.respond(incomingData)
        }
    }
}