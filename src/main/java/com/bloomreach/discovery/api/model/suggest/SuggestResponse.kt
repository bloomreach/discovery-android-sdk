/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.suggest

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class SuggestResponse(
    @JsonProperty("queryContext")
    val queryContext: QueryContext? = null,

    @JsonProperty("suggestionGroups")
    val suggestionGroups: List<SuggestionGroup>? = null
) : BaseResponse()