/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

import com.bloomreach.discovery.api.ApiConstants.REQUEST_TYPE
import com.bloomreach.discovery.api.ApiConstants.REQUEST_TYPE_SEARCH
import com.bloomreach.discovery.api.ApiConstants.SEARCH_TYPE
import com.bloomreach.discovery.api.ApiConstants.SEARCH_TYPE_KEYWORD

/**
 * Product Search Request Object class. Create the object of this class in order to
 * send it with Product Search API
 */
class ProductSearchRequest(): SearchRequest<ProductSearchRequest>() {

    // add hardcoded default parameters required for product search API
    init {
        setRequestType()
        setSearchType()
    }

    /**
     * Method to set hardcoded default parameters required for product search API
     * @return  A reference request object
     */
    private fun setRequestType(): ProductSearchRequest {
        return set(REQUEST_TYPE, REQUEST_TYPE_SEARCH)
    }

    /**
     * Method to set hardcoded default parameters required for product search API
     * @return  A reference request object
     */
    private fun setSearchType(): ProductSearchRequest {
        return set(SEARCH_TYPE, SEARCH_TYPE_KEYWORD)
    }
}