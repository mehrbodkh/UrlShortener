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
            port = config.property("ktor.deployment.port").getString().toInt()
            host = config.property("ktor.deployment.host").getString()
        }
    })
        .start(wait = true)
}

fun Application.module() {
    configureDatabase()
    configureSerialization()
    configureRouting()
}
