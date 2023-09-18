/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.rp

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class RecsAndPathwaysResponse(
    @JsonProperty("metadata")
    val metadata: Metadata? = null,

    @JsonProperty("response")
    val response: Response? = null
) : BaseResponse()