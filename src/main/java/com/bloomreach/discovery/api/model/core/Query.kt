/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Query(
    @JsonProperty("modification")
    val modification: Modification? = null,

    @JsonProperty("didYouMean")
    val didYouMean: List<String>? = null
) : BaseResponse()