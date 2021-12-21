package com.apprecipe.rijkscollection.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtListResponse(
    @Json(name = "artObjects") val artObjects: List<ArtItem>
)
