/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class FacetCounts(
    @JsonProperty("facet_fields")
    val facetFields: LinkedHashMap<String, List<FacetFields>>? = null,

    @JsonProperty("facet_queries")
    val facetQueries: LinkedHashMap<String, Int>? = null,

    @JsonProperty("facet_ranges")
    val facetRanges: LinkedHashMap<String, List<FacetRange>>? = null,

    @JsonProperty("facets")
    val facets: List<Facet>? = null
) : BaseResponse()