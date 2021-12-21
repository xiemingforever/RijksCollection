package com.apprecipe.rijkscollection.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ArtDetailResponse(
    @Json(name = "artObject") val artDetail: ArtDetail
)
