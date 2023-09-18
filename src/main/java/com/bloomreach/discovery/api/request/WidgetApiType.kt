/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

/**
 * Widget TYPE ENUM to specify which type on widget API needs to be called.
 * This gets added as Path parameter to he request
 */
enum class WidgetApiType(val value: String) {
    ITEM("item"),
    CATEGORY("category"),
    KEYWORD("keyword"),
    PERSONALIZED("personalized"),
    GLOBAL("global")
}