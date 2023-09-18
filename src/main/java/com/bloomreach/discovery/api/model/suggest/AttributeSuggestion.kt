/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.suggest

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class AttributeSuggestion(
    @JsonProperty("attributeType")
    val attributeType: String? = null,

    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("value")
    val value: String? = null
) : BaseResponse()