/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model.validator.response

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Param Response object from Pixel Validator API response
 */
data class Param(
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("value")
    val value: String
)