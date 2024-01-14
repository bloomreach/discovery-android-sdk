/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.rp

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Metadata(
    @JsonProperty("response")
    val response: MetadataResponse? = null,

    @JsonProperty("widget")
    val widget: Widget? = null

//Query
)  : BaseResponse()