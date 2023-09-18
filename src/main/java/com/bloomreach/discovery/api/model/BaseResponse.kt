/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.model

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter

open class BaseResponse {

    @JsonAnySetter
    @get:JsonAnyGetter
    val otherFields: Map<String, Any?> = hashMapOf()

    fun getOtherField(key: String): Any? {
        return if (otherFields.contains(key)) {
            otherFields[key]
        } else
            null
    }
}