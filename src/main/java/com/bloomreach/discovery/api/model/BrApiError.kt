/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Generic Error class for handling errors from API calls
 *
 * @property errorMessage Formatted error message
 * @property errorCode Error code for additional handling
 */
data class BrApiError(
    @JsonProperty("message")
    val errorMessage: String,

    @JsonProperty("status_code")
    val errorCode: Int
)
