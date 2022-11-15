package com.mehrbod.dao

import com.mehrbod.models.Url

interface DAOFacade {
    suspend fun addShortenedUrl(originalUrl: String, shortenedUrl: String): Url?
    suspend fun originalUrl(shortenedUrl: String): Url?
}