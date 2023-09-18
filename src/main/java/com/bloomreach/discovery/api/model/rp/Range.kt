/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.rp

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Range(
    @JsonProperty("key")
    val key: String? = null,

    @JsonProperty("value")
    val value: List<Value>? = null,
) : BaseResponse()
