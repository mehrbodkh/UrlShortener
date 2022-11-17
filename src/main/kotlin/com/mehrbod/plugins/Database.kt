package com.mehrbod.plugins

import com.mehrbod.data.dao.DatabaseFactory
import io.ktor.server.application.*

fun Application.configureDatabase() {
    DatabaseFactory.init(environment.config)
}