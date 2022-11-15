package com.mehrbod

import com.mehrbod.dao.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.mehrbod.plugins.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

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
    DatabaseFactory.init(
        environment.config.property("ktor.database.host").getString(),
        environment.config.property("ktor.database.port").getString().toInt(),
        environment.config.property("ktor.database.databaseName").getString(),
        environment.config.property("ktor.database.user").getString(),
    )
    configureSerialization()
    configureRouting()
}
