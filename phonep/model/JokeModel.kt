package com.dg.phonep.model

import androidx.annotation.Keep

@Keep
data class JokeModel(
    val id: Int,
    val joke : String,
    val setup : String? = null,
    val delivery: String? = null,
    val fav: Boolean? =null
)