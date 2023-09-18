/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.rp

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Value(
    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("count")
    val count: Int = 0,

    @JsonProperty("start")
    val start: Any? = null,

    @JsonProperty("end")
    val end: Any? = null,

) : BaseResponse()
