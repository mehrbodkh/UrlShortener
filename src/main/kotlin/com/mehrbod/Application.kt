package com.mehrbod

import com.mehrbod.plugins.configureDatabase
import com.mehrbod.plugins.configureRouting
import com.mehrbod.plugins.configureSerialization
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, environment = applicationEngineEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load())

        module {
            module()
        }

        connector {
            port = config.propertyOrNull("ktor.deployment.port")?.getString()?.toInt() ?: 8080
            host = config.propertyOrNull("ktor.deployment.host")?.getString() ?: "0.0.0.0"
        }
    })
        .start(wait = true)
}

fun Application.module() {
    configureDatabase()
    configureSerialization()
    configureRouting()
}
