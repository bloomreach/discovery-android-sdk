/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Doc(
    @JsonProperty("brand")
    val brand: String? = null,

    @JsonProperty("description")
    val description: String? = null,

    @JsonProperty("pid")
    val pid: String? = null,

    @JsonProperty("price")
    val price: Double? = null,

    @JsonProperty("sale_price")
    val salePrice: Double? = null,

    @JsonProperty("thumb_image")
    val thumbImage: String? = null,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("url")
    val url: String? = null,

    @JsonProperty("variants")
    val variants: List<Variant>? = null,
) : BaseResponse()