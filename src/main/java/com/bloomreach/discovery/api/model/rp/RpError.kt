/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.rp

import com.fasterxml.jackson.annotation.JsonProperty

data class RpError(
    @JsonProperty("detail")
    val detail: String? = null,
)
