package com.valentinerutto.shamiri.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesResponse(
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
        @SerialName("air_date")
        val airDate: String?,
        @SerialName("characters")
        val characters: List<String?>?,
        @SerialName("created")
        val created: String?,
        @SerialName("episode")
        val episode: String?,
        @SerialName("id")
        val id: Int?,
        @SerialName("name")
        val name: String?,
        @SerialName("url")
        val url: String?
    )
}