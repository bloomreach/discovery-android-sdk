/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.listener

import com.bloomreach.discovery.api.model.BrApiError

/**
 * Interface to provide callback with response when API is success and Error when API fails
 */
interface BrApiCompletionListener {
    fun onBrApiSuccess(response: Any)
    fun onBrApiFailure(error: BrApiError)
}