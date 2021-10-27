package com.example.doggyphotos.api

import com.squareup.moshi.Json

data class DogApiResponse (
    @Json(name = "message")
    val message: String?) {

}