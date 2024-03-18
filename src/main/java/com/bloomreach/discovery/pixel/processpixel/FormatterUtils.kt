/*
 * Copyright 2022 Bloomreach
 */
package com.bloomreach.discovery.pixel.processpixel

import android.net.Uri
import android.util.Base64
import android.util.Log
import com.bloomreach.discovery.pixel.model.CatalogItem
import com.bloomreach.discovery.pixel.model.PixelBasketItem
import com.bloomreach.discovery.pixel.model.VisitorType
import java.net.URLDecoder
import kotlin.random.Random

/**
 * Utility class for performing string formatting operations
 */
internal object FormatterUtils {

    /**
     * Method to generate a random number
     *
     * @return random number in string
     */
    fun generateRand(): String {
        // generate random Long
        return Random.nextLong().toString()
    }

    /**
     * Method to format Cookie2 value
     * @param uuid Android random string
     * @param hitcount ENUM VisitorType (The hitcount value should be 1 for a new visitor, or 2 for returning visitors.)
     *
     * @return  cookie2 - String  value in 'uid={{UUID}}:v=app:ts=0:hc={{hitcount}}' format
     */
    fun formatCookieValue(uuid: String, hitcount: VisitorType, cdpSegment: String? = null): String {
        // convert uid={{UUID}}:v=app:ts=0:hc={{hitcount}}
        return if(cdpSegment.isNullOrEmpty()) {
            "uid=$uuid:v=app:ts=0:hc=${hitcount.hitCount}"
        } else {
            "uid=$uuid:v=app:ts=0:hc=${hitcount.hitCount}:cdp_segments=${Base64.encodeToString(cdpSegment.toByteArray(), Base64.DEFAULT)}"
        }
    }

    /**
     * Method to format url value
     * @param baseurl Base Url for the merchant provided bt Bloomreach
     * @param pType Page classification type
     * @param title Title of the screen
     * @return url - String value in 'http://merchantname.app/ptype/title' format
     */
    fun formatUrl(baseurl: String, pType: String, title: String): String {
        // convert in format http://merchantname.app/ptype/title
        return "$baseurl$pType/$title"
    }

    /**
     * Method to format catalog value
     * The catalog name is encoded by prefixing "cat" + "the index of the catalog starting from 0" + "=" + "the catalog name"
     * @param catalogs Array of the CatalogItem objects
     * @return cataLogs - String value in required format
     */
    fun formatCatalog(catalogs: List<CatalogItem>?): String {

        if (catalogs.isNullOrEmpty()) {
            Log.i(FormatterUtils.javaClass.simpleName, "Catalogs is Null or Empty")
            return ""
        }
        return buildString {
            for ((catalogIndex, catalog) in catalogs.withIndex()) {
                // Each catalog in catalogs is separated by an "!"
                if (catalogIndex != 0) this.append("!")
                // add "cat" + "the index of the catalog starting from 0"
                this.append("cat$catalogIndex=${catalog.name}")

                if (!catalog.viewIds.isNullOrEmpty()) {
                    for ((viewIndex, viewId) in catalog.viewIds.withIndex()) {
                        // Catalog name and view_id are separated by a ":"
                        if (viewIndex == 0) this.append(":")
                        // Multiple view_ids are separated by a "."
                        else this.append(".")
                        // The view_id is encoded by prefixing "v" + "the index of the view_id starting from 0" + "=" + "the view_id"
                        this.append("v$viewIndex=$viewId")
                    }
                }
            }
        }
    }

    /**
     * Method to format basket value
     * Each product in the cart will be separated by !. Each product's details will be separated by '.
     * @param basketItems Array of the PixelBasketItem objects
     * @return basket - String value in required format
     */
    fun formatBasket(basketItems: List<PixelBasketItem>?): String {
        if (basketItems.isNullOrEmpty()) {
            Log.e(FormatterUtils.javaClass.simpleName, "Basket is Null or Empty")
            return ""
        }
        return buildString {
            for (basketItem in basketItems) {
                // add prodId as  '!i<prod_id>'
                this.append("!i${basketItem.prodId}")

                // s<sku> - Sku id, only applies if you have skus.
                if (!basketItem.sku.isNullOrEmpty()) {
                    this.append("'s${basketItem.sku}")
                }

                // n<product_name>
                this.append("'n${basketItem.name}")

                // q<quantity>
                this.append("'q${basketItem.quantity}")

                // p<price> - This should be the unit price per product and not total price. If the item is on sale, this is the unit sale price.
                this.append("'p${basketItem.price}")
            }
        }
    }

    /**
     * Method to convert Map to Uri.Builder for network calls
     *
     * @param queryMap array of the PixelBasketItem objects
     * @return  Uri.Builder - String value in required format
     */
    fun mapToUriBuilder(queryMap: MutableMap<String, String?>): Uri.Builder {
        val uriBuilder = Uri.Builder()
        queryMap.forEach { mapObject ->
            uriBuilder.appendQueryParameter(mapObject.key, mapObject.value ?: "")
        }
        return uriBuilder
    }

    /**
     * Method to convert Map to Uri.Builder for network calls
     *
     * @param queryMap array of the PixelBasketItem objects
     * @return  Uri.Builder - String value in required format
     */
    fun mapToUriBuilderForApi(queryMap: MutableMap<String, Any?>): Uri.Builder {
        val uriBuilder = Uri.Builder()
        queryMap.forEach { mapObject ->
            if(mapObject.value is String) {
                uriBuilder.appendQueryParameter(mapObject.key, mapObject.value as String)
            } else if(mapObject.value is List<*>) { // check for fq
                for(value in mapObject.value as List<*>) {
                    uriBuilder.appendQueryParameter(mapObject.key, value as String)
                }
            }
        }
        return uriBuilder
    }

    private fun getUrlDecodeString(value: String): String {
        return URLDecoder.decode(value, "UTF-8")
    }
}