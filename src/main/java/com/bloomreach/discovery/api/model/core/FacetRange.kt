/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse

@Deprecated("Customers whose go-live date is after September 7, 2023 will be on V3 Facet response format by default. " +
        "If you’re on the legacy format and would like to implement the new Facet response format, kindly contact your Bloomreach Services representative.")
data class FacetRange(
    val start: Any? = null,
    val end: Any? = null,
    val count: Int? = null,
) : BaseResponse()
