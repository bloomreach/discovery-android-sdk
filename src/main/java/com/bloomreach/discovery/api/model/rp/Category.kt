/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.rp

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Category(
    @JsonProperty("cat_id")
    val catId: String? = null,

    @JsonProperty("cat_name")
    val catName: String? = null,

    @JsonProperty("count")
    val count: Int = 0,

    @JsonProperty("children")
    val children: List<Category>? = null,
) : BaseResponse()
