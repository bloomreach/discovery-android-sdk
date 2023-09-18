/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model

/**
 * GroupType ENUM for events pixels group
 */
enum class GroupType(val group: String) {
    CART("cart"),
    SUGGEST("suggest"),
    PRODUCT("product"),
    WIDGET("widget")
}