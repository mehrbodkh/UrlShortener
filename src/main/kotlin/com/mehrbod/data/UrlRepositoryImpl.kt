package com.mehrbod.data

import com.mehrbod.data.dao.DAOFacade
import com.mehrbod.data.models.asExternalModel
import com.mehrbod.models.Url

class UrlRepositoryImpl(
    private val dao: DAOFacade
) : UrlRepository {

    override suspend fun addUrl(originalUrl: String, shortenedUrl: String): Url? {
        return dao.addShortenedUrl(originalUrl, shortenedUrl)?.asExternalModel()
    }

    override suspend fun fetchUrl(shortenedUrl: String): Url? {
        return dao.originalUrl(shortenedUrl)?.asExternalModel()
    }
}
