/*
 * Copyright 2023 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Facet(
    @JsonProperty("name")
    var name: String? = null,
    @JsonProperty("type")
    var type: String? = null,
    @JsonProperty("value")
    var value: FacetValue? = null
): BaseResponse()
