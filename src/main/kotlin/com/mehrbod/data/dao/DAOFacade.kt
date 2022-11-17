package com.mehrbod.data.dao

import com.mehrbod.data.models.UrlRow

interface DAOFacade {
    suspend fun addShortenedUrl(originalUrl: String, shortenedUrl: String): UrlRow?
    suspend fun originalUrl(shortenedUrl: String): UrlRow?
}