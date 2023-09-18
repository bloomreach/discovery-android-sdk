/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

import com.bloomreach.discovery.api.ApiConstants
import java.util.stream.Collectors

/**
 * AutoSuggest Request Object class. Create the object of this class in order to
 * send it with AutoSuggest API
 */
class AutosuggestRequest : RequestMap<AutosuggestRequest>() {
    // add hardcoded default parameters required for product search API
    init {
        setRequestType()
    }

    /**
     * Method to set hardcoded default parameters required for Auto Suggest API
     * @return  A reference request object
     */
    private fun setRequestType(): AutosuggestRequest {
        return set(ApiConstants.REQUEST_TYPE, ApiConstants.REQUEST_TYPE_SUGGEST)
    }

    /**
     * Method to set catalog views that you want to see in your suggestions.
     *
     * @param value  catalogs views formatted in required format
     *
     * @return  A reference request object
     */
    fun catalogViews(value: String): AutosuggestRequest {
        return set(ApiConstants.CATALOG_VIEWS, value)
    }

    /**
     * Method to set catalog views that you want to see in your suggestions.
     * This method helps to format the catalogs views in required format
     *
     * @param values Map of catalog views attributes and its values
     *
     * @return  A reference request object
     */
    fun catalogViews(values: Map<String, String>): AutosuggestRequest {
        //converts to my_product_catalog:store1|recipe:daily
        val catalogViewsStr = values.entries
            .stream()
            .map { e -> e.key + ":" + e.value }
            .collect(Collectors.joining("|"))
        return catalogViews(catalogViewsStr)
    }

    /**
     * Method to set search term for Search APIs
     *
     * @param q  Partial search query that Autosuggest should operate on.
     *
     * @return  A reference to the current Request object
     */
    fun searchTerm(q: String): AutosuggestRequest {
        return set(ApiConstants.SEARCH_TERM, q)
    }

    /**
     * The user agent of the device that's making the request.
     *
     * @param value user agent value
     *
     * @return  A reference request object
     */
    fun userAgent(value: String): AutosuggestRequest {
        return set(ApiConstants.USER_AGENT, value)
    }

    /**
     * Method to set url
     *
     * @param value The title or name of the product.
     *
     * @return  A reference to the current Request object
     */
    fun url(value: String): AutosuggestRequest {
        return set(ApiConstants.URL, value)
    }

    /**
     * Method to set user id of the customer
     *
     * @param value The universal customer ID of the user.
     *
     * @return  A reference to the current Request object
     */
    fun userId(value: String?): AutosuggestRequest {
        return set(ApiConstants.USER_ID, value)
    }
}