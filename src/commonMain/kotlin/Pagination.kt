package cloud.drakon.ktxivapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("Page") val page: Int,
    @SerialName("PageNext") val pageNext: Int,
    @SerialName("PagePrev") val pagePrev: Int?,
    @SerialName("PageTotal") val pageTotal: Int,
    @SerialName("Results") val results: Int,
    @SerialName("ResultsPerPage") val resultsPerPage: Byte,
    @SerialName("ResultsTotal") val resultsTotal: Int
)
