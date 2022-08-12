package com.hero.ataa.data.local.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.hero.ataa.domain.models.SearchHistory
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object SearchHistorySerializer : Serializer<SearchHistory> {

    override val defaultValue = SearchHistory()

    override suspend fun readFrom(input: InputStream): SearchHistory {
        try {
            return Json.decodeFromString(
                SearchHistory.serializer(), input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read UserPrefs", serialization)
        }
    }

    override suspend fun writeTo(t: SearchHistory, output: OutputStream) {
        output.write(
            Json.encodeToString(SearchHistory.serializer(), t)
                .encodeToByteArray()
        )
    }
}