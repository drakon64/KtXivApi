package cloud.drakon.ktxivapi.content

import cloud.drakon.ktxivapi.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentSearch(
    @SerialName("Pagination") val pagination: Pagination,
    @SerialName("Results") val results: List<ContentResult>
)
