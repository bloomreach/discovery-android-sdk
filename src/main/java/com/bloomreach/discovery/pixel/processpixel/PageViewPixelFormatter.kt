/*
 * Copyright 2022 Bloomreach
 */
package com.bloomreach.discovery.pixel.processpixel

import com.bloomreach.discovery.pixel.model.PixelObject

/**
 * Class for creating query parameter as String for each Page View Pixel in required format
 */
internal class PageViewPixelFormatter {

    /**
     * Method to generating query parameter String for Product Page View Pixel
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @param queryMap reference of Map where the values will be added
     * @return Map with value in required format
     */
    fun prepareProductPageViewQuery(pixelObject: PixelObject, queryMap: MutableMap<String, String?>): MutableMap<String, String?> {
        queryMap.put("prod_id", pixelObject.prodId)
        queryMap.put("prod_name", pixelObject.prodName)
        if(pixelObject.prodSku != null) {
            queryMap.put("sku", pixelObject.prodSku)
        }
        return queryMap
    }

    /**
     * Method to generating query parameter String for Content Page View Pixel
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @param queryMap reference of Map where the values will be added
     * @return Map with value in required format
     */
    fun prepareContentPageViewQuery(pixelObject: PixelObject, queryMap: MutableMap<String, String?>): MutableMap<String, String?> {
        if (!pixelObject.catalogs.isNullOrEmpty()) {
            queryMap.put("catalogs", FormatterUtils.formatCatalog(pixelObject.catalogs))
        }
        queryMap.put("item_id", pixelObject.itemId)
        queryMap.put("item_name", pixelObject.itemName)
        return queryMap
    }

    /**
     * Method to generating query parameter String for Content Search Page View Pixel
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @param queryMap reference of Map where the values will be added
     * @return Map with values in required format
     */
    fun prepareContentSearchPageViewQuery(pixelObject: PixelObject, queryMap: MutableMap<String, String?>): MutableMap<String, String?>   {
        if (!pixelObject.catalogs.isNullOrEmpty()) {
            queryMap.put("catalogs", FormatterUtils.formatCatalog(pixelObject.catalogs))
        }
        queryMap.put("search_term", pixelObject.searchTerm)
        return queryMap
    }

    /**
     * Method to generating query parameter String for Search Page View Pixel
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @param queryMap reference of Map where the values will be added
     * @return Map with values in required format
     */
    fun prepareSearchPageViewQuery(pixelObject: PixelObject,  queryMap: MutableMap<String, String?>): MutableMap<String, String?>   {
        queryMap.put("search_term", pixelObject.searchTerm)
        return queryMap
    }

    /**
     * Method to generating query parameter String for Category Page View Pixel
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @param queryMap reference of Map where the values will be added
     * @return Map with values in required format
     */
    fun prepareCategoryPageViewQuery(pixelObject: PixelObject,  queryMap: MutableMap<String, String?>): MutableMap<String, String?> {
        queryMap.put("cat", pixelObject.cat)
        queryMap.put("cat_id", pixelObject.catId)
        return queryMap
    }

    /**
     * Method to generating query parameter String for Conversion Page View Pixel
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @param queryMap reference of Map where the values will be added
     * @return Map with values in required format
     */
    fun prepareConversionPageViewQuery(pixelObject: PixelObject, queryMap: MutableMap<String, String?>): MutableMap<String, String?>  {
        queryMap.put("is_conversion", pixelObject.isConversion.toString())
        queryMap.put("basket_value", pixelObject.basketValue)
        queryMap.put("order_id", pixelObject.orderId)
        queryMap.put("basket", FormatterUtils.formatBasket(pixelObject.basket))
        return queryMap
    }
}