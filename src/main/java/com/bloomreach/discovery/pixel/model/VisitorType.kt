/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model

/**
 * VisitorType ENUM to provide hitcount based on new or returning user
 */
enum class VisitorType(val hitCount: Int) {
    NEW_USER(1),
    RETURNING_USER(2)
}