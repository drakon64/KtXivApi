package cloud.drakon.ktxivapi.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentResult(
    @SerialName("ID") val id: Int,
    @SerialName("Icon") val icon: String,
    @SerialName("Name") val name: String,
    @SerialName("Url") val url: String,
)
