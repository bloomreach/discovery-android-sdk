/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Response(
    @JsonProperty("docs")
    val docs: List<Doc>? = null,

    @JsonProperty("numFound")
    val numFound: Int? = null,

    @JsonProperty("start")
    val start: Int? = null
) : BaseResponse()