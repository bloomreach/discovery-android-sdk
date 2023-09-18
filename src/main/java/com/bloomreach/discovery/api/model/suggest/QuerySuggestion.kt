/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.suggest

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class QuerySuggestion(
    @JsonProperty("displayText")
    val displayText: String? = null,

    @JsonProperty("query")
    val query: String? = null
) : BaseResponse()