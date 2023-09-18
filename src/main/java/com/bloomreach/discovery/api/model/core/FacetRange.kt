/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse

data class FacetRange(
    val start: Any? = null,
    val end: Any? = null,
    val count: Int? = null,
) : BaseResponse()
