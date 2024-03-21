package com.example.marsphotos.network

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MarsPhoto (
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)