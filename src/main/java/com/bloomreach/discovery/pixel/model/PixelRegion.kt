/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model

/**
 * PixelRegion ENUM to provide pixel end point based on region
 */
enum class PixelRegion(val url: String) {
    NA("p.brsrvr.com"),
    EU("p-eu.brsrvr.com"),
    TEST("p-test.brsrvr.com")
}