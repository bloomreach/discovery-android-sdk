/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model

/**
 * Model class for Catalog Item
 * @property name Catalog Name
 * @property viewIds Views show selective content to selective users, such as content-based on regions or stores. This can be Single or Multiple.
 */
data class CatalogItem(
    val name: String,
    val viewIds: List<String>? = null
)
