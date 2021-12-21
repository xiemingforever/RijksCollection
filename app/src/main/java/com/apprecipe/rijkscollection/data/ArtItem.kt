package com.apprecipe.rijkscollection.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtItem(
    @Json(name = "objectNumber") val objectNumber: String,
    @Json(name = "title") val title: String,
    @Json(name = "webImage") val webImage: WebImage
)

@JsonClass(generateAdapter = true)
data class WebImage(
    @Json(name = "guid") val guid: String,
    @Json(name = "url") val url: String
)
