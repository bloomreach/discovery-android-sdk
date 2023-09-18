/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model.validator.response

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Response object from Pixel Validator API response
 */
data class PixelValidatorResponse(
    @JsonProperty("displayName")
    val displayName: String,
    @JsonProperty("errors")
    val errors: List<Param>,
    @JsonProperty("merchant")
    val merchant: String,
    @JsonProperty("params")
    val params: List<Param>?,
    @JsonProperty("success")
    val success: List<Param>,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("warns")
    val warns: List<Param>
)