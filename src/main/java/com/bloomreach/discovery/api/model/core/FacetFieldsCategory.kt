/*
 * Copyright 2024 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = JsonDeserializer.None::class)
data class FacetFieldsCategory(
    @JsonProperty("cat_id")
    val catId: String? = null,

    @JsonProperty("cat_name")
    val catName: String? = null,

    @JsonProperty("crumb")
    val crumb: String? = null,

    @JsonProperty("tree_path")
    val treePath: String? = null,

    @JsonProperty("parent")
    val parent: String? = null,
) : FacetValue()