/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

data class FacetCounts(

    @JsonProperty("facet_fields")
    val facetFields: LinkedHashMap<String, List<FacetFields>>? = null,

    @JsonProperty("facet_queries")
    val facetQueries: LinkedHashMap<String, Int>? = null,
//    val facetQueries: LinkedHashMap<String, List<FacetFields>>? = null,

    @JsonProperty("facet_ranges")
    val facetRanges: LinkedHashMap<String, List<FacetRange>>? = null,
) : BaseResponse()