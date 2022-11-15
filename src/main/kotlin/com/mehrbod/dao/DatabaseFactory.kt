package com.mehrbod.dao

import com.mehrbod.models.ShortenedUrls
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val host = config.property("ktor.database.host").getString()
        val port = config.property("ktor.database.port").getString().toInt()
        val databaseName = config.property("ktor.database.databaseName").getString()
        val user = config.property("ktor.database.user").getString()

        val driverClassName = "org.postgresql.Driver"
        val jdbcUrl = "jdbc:postgresql://$host:$port/$databaseName"
        val database = Database.connect(createHikariDataSource(jdbcUrl, driverClassName, user))

        transaction(database) {
            SchemaUtils.create(ShortenedUrls)
        }
    }

    private fun createHikariDataSource(
        url: String,
        driver: String,
        user: String,
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        username = user
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    })
}

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
