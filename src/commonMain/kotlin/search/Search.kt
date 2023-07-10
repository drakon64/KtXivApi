package cloud.drakon.ktxivapi.search

import cloud.drakon.ktxivapi.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Search(
    @SerialName("Pagination") val pagination: Pagination,
    @SerialName("Results") val results: List<SearchResult>,
    @SerialName("SpeedMs") val speedMs: Int
)
