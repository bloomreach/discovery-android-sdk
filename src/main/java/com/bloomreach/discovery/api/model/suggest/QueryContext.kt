/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.suggest

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class QueryContext(
    @JsonProperty("originalQuery")
    val originalQuery: String? = null
) : BaseResponse()