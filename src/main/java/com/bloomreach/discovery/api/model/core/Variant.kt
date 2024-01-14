/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

//TODO check
data class Variant(
    @JsonProperty("skuid")
    val skuId: List<String>? = null
) : BaseResponse()