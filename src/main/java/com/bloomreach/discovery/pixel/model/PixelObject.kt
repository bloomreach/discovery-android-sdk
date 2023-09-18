/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.model

/**
 * Model class for PixelObject. Pixel Object will hold all the fields provided by merchant app
 * and will be used for further process and converting it to required Query Parameter
 */
internal data class PixelObject(
    val type: PixelType, //ENUM
    val pType: PageType, //ENUM
    val title: String,
    val ref: String,

    val eType: String? = null,
    val prodId: String? = null,
    val prodName: String? = null,
    val prodSku: String? = null,
    val prodCollectionId: String? = null,

    val catalogs: List<CatalogItem>? = null, //Array of catalogs
    val itemId: String? = null,
    val itemName: String? = null,

    val isConversion: Int? = null,
    val basketValue: String? = null,
    val orderId: String? = null,
    val basket: List<PixelBasketItem>? = null,

    val group: GroupType? = null,
    val searchTerm: String? = null,
    val typedTerm: String? = null,
    val catId: String? = null,
    val cat: String? = null

)
