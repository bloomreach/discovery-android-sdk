/*
 * Copyright 2023 Bloomreach
 */

package com.bloomreach.discovery.api.model.visualsearch


import com.bloomreach.discovery.api.model.BaseResponse
import com.bloomreach.discovery.api.model.rp.Widget
import com.fasterxml.jackson.annotation.JsonProperty

data class Metadata(
    @JsonProperty("query")
    var query: Query? = null,
    @JsonProperty("widget")
    var widget: Widget? = null
) : BaseResponse()