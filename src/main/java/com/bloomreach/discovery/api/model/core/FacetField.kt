/*
 * Copyright 2024 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = JsonDeserializer.None::class)
class FacetField :  FacetValue()  {
    val name: String? = null
}