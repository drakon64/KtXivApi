package cloud.drakon.ktxivapi

import cloud.drakon.ktxivapi.common.Language
import cloud.drakon.ktxivapi.content.ContentSearch
import cloud.drakon.ktxivapi.search.SortOrder
import cloud.drakon.ktxivapi.search.StringAlgo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.JsonObject

expect val ktorClient: HttpClient

object KtXivApi {
    suspend fun search(
        string: String,
        indexes: List<String>? = null,
        stringAlgo: StringAlgo? = null,
        stringColumn: String? = null,
        page: Int? = null,
        sortField: String? = null,
        sortOrder: SortOrder? = null,
        limit: Byte? = null,
        language: Language? = null,
        pretty: Boolean? = null,
        snakeCase: Boolean? = null,
        columns: List<String>? = null
    ): JsonObject = coroutineScope {
        return@coroutineScope ktorClient.get("search") {
            url {
                parameters.append("string", string)

                if (indexes != null) {
                    for (i in indexes) {
                        parameters.append("indexes", i)
                    }
                }
                if (stringAlgo != null) {
                    parameters.append("string_algo", stringAlgo.name)
                }
                if (stringColumn != null) {
                    parameters.append("string_column", stringColumn)
                }
                if (page != null) {
                    parameters.append("page", page.toString())
                }
                if (sortField != null) {
                    parameters.append("sort_field", sortField)
                }
                if (sortOrder != null) {
                    parameters.append("sort_order", sortOrder.name)
                }
                if (limit != null) {
                    parameters.append("limit", limit.toString())
                }
                if (language != null) {
                    parameters.append("language", language.name)
                }
                if (pretty == true) {
                    parameters.append("pretty", "1")
                }
                if (snakeCase == true) {
                    parameters.append("snake_case", "1")
                }
                if (!columns.isNullOrEmpty()) {
                    parameters.append("columns", columns.joinToString(","))
                }
            }
        }.body()
    }

    suspend fun getContent(): List<String> = coroutineScope {
        return@coroutineScope ktorClient.get("content").body()
    }

    suspend fun getContentName(
        content: String,
        limit: Short? = null,
        ids: List<Int>? = null
    ): ContentSearch = coroutineScope {
        return@coroutineScope ktorClient.get(content) {
            url {
                if (limit != null) {
                    parameters.append("limit", limit.toString())
                }
                if (ids != null) {
                    parameters.append("limit", ids.joinToString(","))
                }
            }
        }.body()
    }

    suspend fun getContentId(content: String, id: Int): JsonObject = coroutineScope {
        return@coroutineScope ktorClient.get("${content}/${id}").body()
    }

    suspend fun getServers(): List<String> = coroutineScope {
        return@coroutineScope ktorClient.get("servers").body()
    }

    suspend fun getServersByDc(): Map<String, List<String>> = coroutineScope {
        return@coroutineScope ktorClient.get("servers/dc").body()
    }
}
