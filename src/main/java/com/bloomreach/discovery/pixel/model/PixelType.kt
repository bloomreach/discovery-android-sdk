/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model

/**
 * PixelType ENUM to provide tne type of Pixel to be sent
 */
enum class PixelType(val type: String) {
    // for page view pixels
    PAGE_VIEW("pageview"),

    // for event pixels
    EVENT("event")
}