package ru.netology.kotlin.dz3_1standroidapp.client

import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.ContentType
import io.ktor.util.KtorExperimentalAPI

object Api {
    const val url = "https://raw.githubusercontent.com/Zimo1/tmp-data/master/output.json"

    //@UseExperimental(KtorExperimentalAPI::class)
    //-Xuse-experimental=kotlin.Experimental
    val client = HttpClient {
        install(JsonFeature) {
            acceptContentTypes = listOf(
                ContentType.Text.Plain,
                ContentType.Application.Json
            )
            serializer = GsonSerializer()
        }
    }
}