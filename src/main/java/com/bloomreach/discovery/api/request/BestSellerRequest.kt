/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

import com.bloomreach.discovery.api.ApiConstants

/**
 * BestSeller Request Object class. Create the object of this class in order to
 * send it with BestSeller API
 */
class BestSellerRequest : SearchRequest<BestSellerRequest>() {

    // add hardcoded default parameters required for BestSeller API
    init {
        setRequestType()
        setSearchType()
    }

    /**
     * Method to set hardcoded default parameters required for BestSeller API
     * @return  A reference request object
     */
    private fun setRequestType(): BestSellerRequest {
        return set(ApiConstants.REQUEST_TYPE, ApiConstants.REQUEST_TYPE_SEARCH)
    }

    /**
     * Method to set hardcoded default parameters required for BestSeller API
     * @return  A reference request object
     */
    private fun setSearchType(): BestSellerRequest {
        return set(ApiConstants.SEARCH_TYPE, ApiConstants.SEARCH_TYPE_BESTSELLER)
    }
}