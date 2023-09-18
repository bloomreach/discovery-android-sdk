/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model.validator

/**
 * Request Body for posting to Pixel Validator call
 */
internal data class PixelValidatorBody(
    val source: String,
    val protocol: String,
    val host: String,
    val port: String,
    val query: String,
    val params: MutableMap<String, String?>,
    val file: String,
    val hash: String,
    val path: String,
    val relative: String,
    val segments: List<String>
)
