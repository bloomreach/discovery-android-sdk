/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Modification(
    @JsonProperty("mode")
    val mode: String? = null,

    @JsonProperty("value")
    val value: String? = null
) : BaseResponse()