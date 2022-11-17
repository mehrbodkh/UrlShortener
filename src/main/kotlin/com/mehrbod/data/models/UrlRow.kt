package com.mehrbod.data.models

import com.mehrbod.models.Url
import org.jetbrains.exposed.sql.Table

data class UrlRow(val id: Int, val originalUrl: String, val shortenedUrl: String)

object ShortenedUrls : Table() {
    val id = integer("id").autoIncrement()
    val originalUrl = varchar("original_url", 512)
    val shortenedUrl = varchar("shortened_url", 80)

    override val primaryKey = PrimaryKey(id)
}

fun UrlRow.asExternalModel() = Url(this.originalUrl, this.shortenedUrl)