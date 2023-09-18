/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

import com.bloomreach.discovery.api.ApiConstants

/**
 * Content Search Request Object class. Create the object of this class in order to
 * send it with Content Search API
 */
class ContentSearchRequest() : SearchRequest<ContentSearchRequest>() {

    // add hardcoded default parameters required for content search API
    init {
        setRequestType()
        setSearchType()
    }

    /**
     * Method to set hardcoded default parameters required for content search API
     * @return  A reference request object
     */
    private fun setRequestType(): ContentSearchRequest {
        return set(ApiConstants.REQUEST_TYPE, ApiConstants.REQUEST_TYPE_SEARCH)
    }

    /**
     * Method to set hardcoded default parameters required for content search API
     * @return  A reference request object
     */
    private fun setSearchType(): ContentSearchRequest {
        return set(ApiConstants.SEARCH_TYPE, ApiConstants.SEARCH_TYPE_KEYWORD)
    }

    /**
     * Method to set catalog name.
     * Named identifier of the catalog. A catalog is a grouping of items into a broader category
     * such as blogs, videos, etc. A catalog is a representation
     * of a group of items and must have a unique name, that is also unique to a domain
     * (if you have multiple sites).
     *
     * @param value  catalog name
     *
     * @return  A reference request object
     */
    fun catalogName(value: String): ContentSearchRequest {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Catalog name cannot be empty")
        }
        return set(ApiConstants.CATALOG_NAME, value)
    }
}