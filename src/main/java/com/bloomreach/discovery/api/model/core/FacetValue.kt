/*
 * Copyright 2023 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = FacetValueDeserializer::class)
open class FacetValue : BaseResponse() {
    val count: Int? = null
}