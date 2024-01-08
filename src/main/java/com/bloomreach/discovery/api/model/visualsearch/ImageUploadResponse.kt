/*
 * Copyright 2023 Bloomreach
 */

package com.bloomreach.discovery.api.model.visualsearch


import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class ImageUploadResponse(
    @JsonProperty("metadata")
    var metadata: Metadata? = null,
    @JsonProperty("response")
    var response: Response? = null
) : BaseResponse()