package com.valentinerutto.shamiri.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("info")
    val info: Info?,
    @SerialName("results")
    val results: List<Result?>?
) {
    @Serializable
    data class Info(
        @SerialName("count")
        val count: Int?,
        @SerialName("next")
        val next: String?,
        @SerialName("pages")
        val pages: Int?,
        @SerialName("prev")
        val prev: Any?
    )

    @Serializable
    data class Result(
        @SerialName("created")
        val created: String?,
        @SerialName("dimension")
        val dimension: String?,
        @SerialName("id")
        val id: Int?,
        @SerialName("name")
        val name: String?,
        @SerialName("residents")
        val residents: List<String?>?,
        @SerialName("type")
        val type: String?,
        @SerialName("url")
        val url: String?
    )
}