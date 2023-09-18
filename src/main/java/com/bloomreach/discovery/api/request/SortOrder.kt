/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

/**
 * ENUM to specify Sort Order Ascending or descending for Search Request
 */
enum class SortOrder(val value: String) {
    ASCENDING("asc"),
    DESCENDING("desc")
}