package com.mehrbod.models

import org.jetbrains.exposed.sql.Table

data class Url(val id: Int, val originalUrl: String, val shortenedUrl: String)

object ShortenedUrls : Table() {
    val id = integer("id").autoIncrement()
    val originalUrl = varchar("original_url", 512)
    val shortenedUrl = varchar("shortened_url", 80)

    override val primaryKey = PrimaryKey(id)
}