/*
 * Copyright 2024 Bloomreach
 */

package com.bloomreach.discovery.api.request

/**
 * SearchMode TYPE ENUM to specify which type Search Mode Loomi Search or Keyword based search
 * This gets added as API parameter to the request
 */
enum class SearchMode(val value: String) {
    //Apply Loomi Search+ mode
    HYBRID("hybrid"),
    //apply Keyword search mode
    STANDARD("standard"),
}

/**
 * API Controls for adjusting Vector search temperature values for one or all queries.
 * This gets added as API parameter to the request
 */
enum class VectorSearchTemperature(val value: String) {
  //  For a compact and very precise recall
    HIGH("high"),
    //For a wider recall,
    STANDARD("standard"),
}