/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse

data class Campaign(
    val id: String? = null,
    val htmlText: String? = null,
    val bannerType: String? = null,
    val keyword: String? = null,
    val name: String? = null,
    val dateEnd: String? = null,
    val dateStart: String? = null
) : BaseResponse()