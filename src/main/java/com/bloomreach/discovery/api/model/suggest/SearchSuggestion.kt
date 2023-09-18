/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.suggest

import com.bloomreach.discovery.api.model.BaseResponse
import com.bloomreach.discovery.api.model.core.Variant
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchSuggestion(
    @JsonProperty("pid")
    val pid: String? = null,

    @JsonProperty("sale_price")
    val salePrice: Double? = null,

    @JsonProperty("thumb_image")
    val thumbImage: String? = null,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("url")
    val url: String? = null,

    @JsonProperty("variants")
    val variants: List<Variant>? = null
) : BaseResponse()