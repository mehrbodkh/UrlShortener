package com.mehrbod.dao

import com.mehrbod.models.Url
import com.mehrbod.models.ShortenedUrls
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToShortenedUrl(row: ResultRow) = Url(
        id = row[ShortenedUrls.id],
        shortenedUrl = row[ShortenedUrls.shortenedUrl],
        originalUrl = row[ShortenedUrls.originalUrl]
    )

    override suspend fun addShortenedUrl(originalUrl: String, shortenedUrl: String) = dbQuery {
        ShortenedUrls.insert {
            it[ShortenedUrls.originalUrl] = originalUrl
            it[ShortenedUrls.shortenedUrl] = shortenedUrl
        }.resultedValues?.singleOrNull()?.let(::resultRowToShortenedUrl)
    }

    override suspend fun originalUrl(shortenedUrl: String): Url? = dbQuery {
        ShortenedUrls.select {
            ShortenedUrls.shortenedUrl eq shortenedUrl
        }
            .map(::resultRowToShortenedUrl)
            .singleOrNull()
    }
}