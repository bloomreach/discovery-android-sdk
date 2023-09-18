/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model

/**
 * Class containing product details for the purchase.
 * @property prodId Product Id of the purchased product
 * @property name Name of the purchased product
 * @property sku SKU number of the purchased product
 * @property quantity Purchased quantity of the product
 * @property price Price of the product
 */
data class PixelBasketItem(
    val prodId: String,
    val name: String,
    val sku: String?,
    val quantity: String,
    val price: String
)
