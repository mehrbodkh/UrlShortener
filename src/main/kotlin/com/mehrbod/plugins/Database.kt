package com.mehrbod.plugins

import com.mehrbod.dao.DatabaseFactory
import io.ktor.server.application.*

fun Application.configureDatabase() {
    DatabaseFactory.init(
        environment.config.property("ktor.database.host").getString(),
        environment.config.property("ktor.database.port").getString().toInt(),
        environment.config.property("ktor.database.databaseName").getString(),
        environment.config.property("ktor.database.user").getString(),
    )
}