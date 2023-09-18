/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.suggest

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class SuggestionGroup(
    @JsonProperty("attributeSuggestions")
    val attributeSuggestions: List<AttributeSuggestion>? = null,

    @JsonProperty("catalogName")
    val catalogName: String? = null,

    @JsonProperty("querySuggestions")
    val querySuggestions: List<QuerySuggestion>? = null,

    @JsonProperty("searchSuggestions")
    val searchSuggestions: List<SearchSuggestion>? = null,

    @JsonProperty("view")
    val view: String? = null
) : BaseResponse()