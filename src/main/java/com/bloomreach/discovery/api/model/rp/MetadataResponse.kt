/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.rp

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class MetadataResponse(
    @JsonProperty("fallback")
    val fallback: String? = null,

    @JsonProperty("personalized_results")
    val personalizedResults: Boolean? = null,

    @JsonProperty("recall")
    val recall: String? = null
) : BaseResponse()
