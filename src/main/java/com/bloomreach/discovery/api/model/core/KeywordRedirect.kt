/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model.core

import com.bloomreach.discovery.api.model.BaseResponse

data class KeywordRedirect(
    val originalQuery: String? = null,
    val redirectedQuery: String? = null,
    val redirectedUrl: String? = null
) : BaseResponse()