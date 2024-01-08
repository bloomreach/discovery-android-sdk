/*
 * Copyright 2023 Bloomreach
 */

package com.bloomreach.discovery.api.model.visualsearch


import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Query(
    @JsonProperty("image_id")
    var imageId: String? = null
) : BaseResponse()