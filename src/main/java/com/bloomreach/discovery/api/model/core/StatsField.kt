/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse

data class StatsField(
    val min: Double = 0.0,
    val max: Double = 0.0
) : BaseResponse()