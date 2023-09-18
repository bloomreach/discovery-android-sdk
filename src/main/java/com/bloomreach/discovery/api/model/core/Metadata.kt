/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Metadata(
    @JsonProperty("query")
    val query: Query? = null
) : BaseResponse()

