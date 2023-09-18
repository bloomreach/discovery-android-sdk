/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.rp

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Widget(
    @JsonProperty("description")
    val description: String? = null,

    @JsonProperty("id")
    val id: String? = null,

    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("rid")
    val rid: String? = null,

    @JsonProperty("type")
    val type: String? = null
) : BaseResponse()