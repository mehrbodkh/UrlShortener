package com.mehrbod.data

import com.mehrbod.models.Url

interface UrlRepository {
    suspend fun addUrl(originalUrl: String, shortenedUrl: String): Url?
    suspend fun fetchUrl(shortenedUrl: String): Url?
}