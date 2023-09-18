/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class CoreResponse(
    @JsonProperty("category_map")
    val categoryMap: LinkedHashMap<String, String>? = null,

    @JsonProperty("did_you_mean")
    val didYouMean: List<String>? = null,

    @JsonProperty("facet_counts")
    val facetCounts: FacetCounts? = null,

    @JsonProperty("response")
    val response: Response? = null,

    @JsonProperty("campaign")
    val campaign: Campaign? = null,

    @JsonProperty("stats")
    val stats: Stats? = null,

    @JsonProperty("keywordRedirect")
    val keywordRedirect: KeywordRedirect? = null,

    @JsonProperty("Metadata")
    val metadata: Metadata? = null,

    @JsonProperty("autoCorrectQuery")
    val autoCorrectQuery: String? = null
) : BaseResponse() {
    fun getCategory(key: String): String? {
        return if (categoryMap?.contains(key) == true) {
            categoryMap[key]
        } else
            null
    }
}