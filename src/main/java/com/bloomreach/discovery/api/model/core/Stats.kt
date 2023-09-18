/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Stats(
    @JsonProperty("stats_fields")
    val statsFields: LinkedHashMap<String, StatsField>? = null
) : BaseResponse() {
    fun getStatsField(key: String): StatsField {
        return statsFields?.get(key)!!
    }
}