package cloud.drakon.ktxivapi.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    @SerialName("ID") val id: Int,
    @SerialName("Icon") val icon: String,
    @SerialName("Name") val name: String,
    @SerialName("Url") val url: String,
    @SerialName("UrlType") val urlType: String
)
