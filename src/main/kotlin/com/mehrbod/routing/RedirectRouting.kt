package com.mehrbod.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.math.BigInteger
import java.security.MessageDigest

val memory = mutableMapOf<String, String>()

fun Route.redirectRoute() {
    get("/{link}") {
        val shortUrl = call.parameters["link"]
        call.respondRedirect(memory[shortUrl]!!)
    }
}

fun String.encodeToID(): String {
    val hashBytes = MessageDigest.getInstance("MD5").digest(this.toByteArray(Charsets.UTF_8))
    val hashString = String.format("%032x", BigInteger(1, hashBytes))
    val truncatedHashString = hashString.take(6)
    return truncatedHashString
}