/*
 * Copyright 2024 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = JsonDeserializer.None::class)
data class FacetRnge(
    val start: Any? = null,
    val end: Any? = null,
) : FacetValue()