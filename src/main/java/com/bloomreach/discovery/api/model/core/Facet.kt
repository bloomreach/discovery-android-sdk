/*
 * Copyright 2023 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty

data class Facet(
    @JsonProperty("name")
    var name: String? = null,
    @JsonProperty("type")
    var type: String? = null,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("value")
    var value: List<FacetValue>? = null
): BaseResponse()
