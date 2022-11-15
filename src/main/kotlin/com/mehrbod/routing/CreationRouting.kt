package com.mehrbod.routing

import com.mehrbod.dao.DAOFacade
import com.mehrbod.dao.DAOFacadeImpl
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.math.BigInteger
import java.security.MessageDigest

@Serializable
data class InputRequest(
    val url: String
)

@Serializable
data class Response(
    val shortUrl: String
)

val dao: DAOFacade = DAOFacadeImpl()

fun Route.creationRouting() {
    route("/create") {
        post("/api/url") {
            val incomingData = call.receive<InputRequest>()
            val shortened = incomingData.url.getShortUrl()
            dao.addShortenedUrl(incomingData.url, shortened)
            call.respond(Response(shortened))
        }
    }
}

fun String.getShortUrl(): String {
    return this.encodeToID()
}

private fun String.encodeToID(): String {
    val hashBytes = MessageDigest.getInstance("MD5").digest(this.toByteArray(Charsets.UTF_8))
    val hashString = String.format("%032x", BigInteger(1, hashBytes))
    return hashString.take(8)
}
