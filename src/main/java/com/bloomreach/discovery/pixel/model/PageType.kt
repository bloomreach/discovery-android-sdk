/*
 * Copyright 2022 Bloomreach
 */
package com.bloomreach.discovery.pixel.model

/**
 * PageType ENUM to provide page name (pType parameter) for Pixels
 */
enum class PageType(val pType: String) {
    // Any home page or landing page that is considered a home page needs to be classified as
    HOME_PAGE("homepage"),

    // Any product, product bundle, product collection or sku set pages need to be classified as
    PRODUCT_PAGE("product"),

    // Any category listing pages, category product listing pages or pages that you consider a category page need to be classified as
    CATEGORY_PAGE("category"),

    // Any search listing or search results pages need to be classified as
    SEARCH_PAGE("search"),

    // Any content pages need to be classified as
    CONTENT_PAGE("content"),

    // Bloomreach Thematic pages need to be classified as
    THEMATIC_PAGE("thematic"),

    // Any Conversion/ Thank You pages as well as any page types
    CONVERSION("conversion"),

    //Any page types that are not one of the above need to be classified as
    OTHER_PAGE("other")
}