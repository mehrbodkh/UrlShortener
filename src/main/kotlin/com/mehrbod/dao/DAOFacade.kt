package com.mehrbod.dao

import com.mehrbod.models.ShortenedUrl

interface DAOFacade {
    suspend fun addShortenedUrl(originalUrl: String, shortenedUrl: String): ShortenedUrl?
    suspend fun originalUrl(shortenedUrl: String): ShortenedUrl?
}