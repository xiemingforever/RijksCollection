package com.apprecipe.rijkscollection.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtDetail(
    @Json(name = "objectNumber") val objectNumber: String,
    @Json(name = "title") val title: String,
    @Json(name = "longTitle") val longTitle: String,
    @Json(name = "webImage") val webImage: WebImage,
    @Json(name = "plaqueDescriptionEnglish") val descriptionEN: String?,
    @Json(name = "plaqueDescriptionDutch") val descriptionNL: String?,
    @Json(name = "subTitle") val subTitle: String,
)
