package com.mehrbod.dao

import com.mehrbod.models.ShortenedUrls
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(host: String, port: Int, databaseName: String, user: String) {
        val driverClassName = "org.postgresql.Driver"
        val jdbcUrl = "jdbc:postgresql://$host:$port/$databaseName"
        val database = Database.connect(jdbcUrl, driverClassName, user = user)

        transaction(database) {
            SchemaUtils.create(ShortenedUrls)
        }
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }