package cloud.drakon.ktxivapi

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual val ktorClient = HttpClient(Js) {
    defaultRequest {
        url("https://xivapi.com/")
    }

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}
