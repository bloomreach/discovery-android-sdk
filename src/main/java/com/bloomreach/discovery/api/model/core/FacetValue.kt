/*
 * Copyright 2023 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

class FacetValue : BaseResponse() {

    @JsonProperty("cat_id")
    val catId: String? = null

    @JsonProperty("cat_name")
    val catName: String? = null

    @JsonProperty("crumb")
    val crumb: String? = null

    @JsonProperty("tree_path")
    val treePath: String? = null

    @JsonProperty("parent")
    val parent: String? = null

    val start: Any? = null
    val end: Any? = null
    val count: Int? = null
    val name: String? = null

}