/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

import com.bloomreach.discovery.api.ApiConstants
import com.bloomreach.discovery.api.ApiConstants.DEFAULT_FACET_FLAG

/**
 * Widget API Request Object class. Create the object of this class in order to
 * send it with Recommendation Search API
 */
class WidgetRequest() : RequestMap<WidgetRequest>() {

    // add default parameters required for search API
    init {
        facet(DEFAULT_FACET_FLAG)
    }

    /**
     * Method to set rows
     *
     * @param rows The number of matching items to return per results page in the API response. The maximum value is 200.
     *
     * @return  A reference to the current Request object
     */
    fun rows(rows: Int): WidgetRequest {
        return set(ApiConstants.ROWS, rows.toString())
    }

    /**
     * Method to set start
     *
     * @param start The number of the first item on a page of results. For example, the first item on the first page is 0, making the start value also 0.
     *
     * @return  A reference to the current Request object
     */
    fun start(start: Int): WidgetRequest {
        return set(ApiConstants.START, start.toString())
    }

    /**
     * Method to set itemIds for  Item Based Widget API
     *
     * @param value  Specifies access to a particular set of items. For product catalog, this would be the PID(s).
     *
     * @return  A reference of Request object
     */
    fun itemIds(value: String): WidgetRequest {
        if (value.isEmpty()) {
            throw IllegalArgumentException("ItemIds can't be empty")
        }
        return set(ApiConstants.ITEM_IDS, value)
    }

    /**
     * Method to set itemIds for  Item Based Widget API
     *
     * @param values Array of access to a particular set of items. For product catalog, this would be the PID(s).
     *
     * @return  A reference to the current Request object
     */
    fun itemIds(values: Array<String>): WidgetRequest {
        var itemIds = ""
        if (values.isNullOrEmpty()) {
            throw IllegalArgumentException()
        } else if (values.size == 1) {
            itemIds = values[0]
        } else if (values.size > 1) {
            itemIds = values.joinToString(",")
        }
        return itemIds(itemIds)
    }

    /**
     * Method to set itemIds for  Item Based Widget API
     *
     * @param values List of access to a particular set of items. For product catalog, this would be the PID(s).
     *
     * @return  A reference to the current Request object
     */
    fun itemIds(values: List<String>): WidgetRequest {
        var itemIds = ""
        if (values.isNullOrEmpty()) {
            throw IllegalArgumentException("ItemIds can't be empty")
        } else if (values.size == 1) {
            itemIds = values[0]
        } else if (values.size > 1) {
            itemIds = values.joinToString(",")
        }
        return itemIds(itemIds)
    }

    /**
     * Method to set cat Id for Category Based Widget API
     *
     * @param value Your site's category ID.
     *
     * @return  A reference of Request object
     */
    fun catId(value: String): WidgetRequest {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Category Id can't be empty")
        }
        return set(ApiConstants.CAT_ID, value)
    }

    /**
     * Method to set search query for Keyword and Personalization Based Widget API
     *
     * @param value  search query.
     *
     * @return  A reference of Request object
     */
    fun query(value: String): WidgetRequest {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Search query Id can't be empty")
        }
        return set(ApiConstants.QUERY, value)
    }

    /**
     * Method to set user id required for Personalization-based Recommendation widgets
     *
     * @param value  The universal customer ID of the user.
     *
     * @return  A reference of Request object
     */
    fun userId(value: String): WidgetRequest {
        if (value.isEmpty()) {
            throw IllegalArgumentException("User Id can't be empty")
        }
        return set(ApiConstants.USER_ID, value)
    }

    /**
     * Method to set context id Item-based Recommendation widget API
     *
     * @param value takes a single product ID for producing Context-based merchandising results for the widget.
     *
     * @return  A reference of Request object
     */
    fun contextId(value: String?): WidgetRequest {
        return set(ApiConstants.CONTEXT_ID, value)
    }

    /**
     * Method to set fields Recommendation widget APIs
     *
     * @param value A formatted comma-separated list of fields to be sent in the request.
     *
     * @return  A reference of Request object
     */
    fun fields(value: String?): WidgetRequest {
        return set(ApiConstants.FIELDS, value)
    }

    /**
     * Method to set fields Recommendation widget API
     *
     * @param values  List of fields to be sent in the request.
     *
     * @return  A reference of Request object
     */
    fun fields(values: List<String>?): WidgetRequest {
        var fieldString: String? = null
        if (values.isNullOrEmpty()) {
            fieldString = null
        } else if (values.size == 1) {
            fieldString = values[0]
        } else if (values.size > 1) {
            fieldString = values.joinToString(",")
        }
        return fields(fieldString)
    }

    /**
     * Method to set fields Recommendation widget API
     *
     * @param values  Array of fields to be sent in the request.
     *
     * @return  A reference of Request object
     */
    fun fields(values: Array<String>?): WidgetRequest {
        var fieldString: String? = null
        if (values.isNullOrEmpty()) {
            fieldString = null
        } else if (values.size == 1) {
            fieldString = values[0]
        } else if (values.size > 1) {
            fieldString = values.joinToString(",")
        }
        return fields(fieldString)
    }

    /**
     * Method to set filter facet for Keyword and Category Recommendation widget APIs
     *
     * @param value A formatted value to be sent in the request.
     *
     * @return  A reference of Request object
     */
    fun filterFacet(value: String): WidgetRequest {
        return add(ApiConstants.FILTER_FACET, value)
    }

    /**
     * Method to set filter facet for Keyword and Category Recommendation widget APIs
     *
     * @param attribute filter facet attribute
     * @param value value for the given attribute
     *
     * @return  A reference of Request object
     */
    fun filterFacet(attribute: String, value: String): WidgetRequest {
        return filterFacet("$attribute:\"${value}\"")
    }

    /**
     * Method to set filter facet for Keyword and Category Recommendation widget APIs
     *
     * @param attribute filter facet attribute
     * @param values The list of multiple possible values for given attribute.
     * @param operator 'AND' or 'OR' operator for values
     *
     * @return  A reference of Request object
     */
    fun filterFacet(attribute: String, values: List<String>, operator: Operator): WidgetRequest {
        var str = "$attribute:"
        if (values.size > 1) {
            //attribute:"value 1" OR "value 2"
            for ((index, value) in values.withIndex()) {
                str += "\"${value}\""
                if (index != values.size - 1) {
                    if (operator == Operator.OR) {
                        str += " OR "
                    } else if (operator == Operator.AND) {
                        str += " AND "
                    }
                }
            }
            return filterFacet(str)
        } else {
            return filterFacet(attribute, values[0])
        }
    }

    /**
     * Method to View Id
     *
     * @param value A unique identifier for a specific view of your product catalog.
     *
     * @return  A reference of Request object
     */
    fun viewId(value: String?): WidgetRequest {
        return set(ApiConstants.VIEW_ID, value)
    }

    /**
     * Method to apply facet
     *
     * @param value Boolean value to enable or disable facet filtering
     *
     * @return  A reference of Request object
     */
    fun facet(value: Boolean): WidgetRequest {
        return set(ApiConstants.FACET, value.toString())
    }

    /**
     * Method to set filter for Recommendation widget APIs
     *
     * @param value The formatted value for given
     *
     * @return  A reference of Request object
     */
    fun filter(value: String): WidgetRequest {
        return add(ApiConstants.FILTER, value)
    }

    /**
     * Method to set filter for Recommendation widget APIs
     *
     * @param attribute filter attribute value
     * @param value The formatted value for given  attribute
     * @param isNot if '-' is needed at start pass value as true. Default set to false,
     *
     * @return  A reference of Request object
     */
    fun filter(attribute: String, value: String, isNot: Boolean = false): WidgetRequest {
        if (isNot) {
            return filter("-($attribute:(\"${value}\"))")
        } else {
            return filter("($attribute:(\"${value}\"))")
        }
    }

    /**
     * Method to set filter with range for Recommendation widget APIs
     *
     * @param attribute filter attribute value
     * @param startRange The start value for range filter
     * @param endRange The end value for range filter
     * @param isNot if '-' is needed at start pass value as true. Default set to false,
     *
     * @return  A reference of Request object
     */
    fun filter(
        attribute: String,
        startRange: String,
        endRange: String,
        isNot: Boolean = false
    ): WidgetRequest {
        if (isNot) {
            return filter("-($attribute:[\"${startRange}\" TO \"${endRange}\"])")
        } else {
            return filter("($attribute:[\"${startRange}\" TO \"${endRange}\"])")
        }
    }

    /**
     * Method to set filter with range for Recommendation widget APIs
     *
     * @param attribute filter attribute value
     * @param startRange The start value for range filter
     * @param endRange The end value for range filter
     * @param isNot if '-' is needed at start pass value as true. Default set to false,
     *
     * @return  A reference of Request object
     */
    fun filter(
        attribute: String,
        startRange: Int,
        endRange: Int,
        isNot: Boolean = false
    ): WidgetRequest {
        return filter(attribute, startRange.toString(), endRange.toString(), isNot)
    }

    /**
     * Method to set filter with range for Recommendation widget APIs
     *
     * @param attribute filter attribute value
     * @param range The range(0..100) value for range filter
     * @param isNot if '-' is needed at start pass value as true. Default set to false,
     *
     * @return  A reference of Request object
     */
    fun filter(attribute: String, range: IntRange, isNot: Boolean = false): WidgetRequest {
        return filter(attribute, range.first.toString(), range.last.toString(), isNot)
    }

    /**
     * Method to set filter with attribute and its multiple values
     *
     * @param attribute The attribute for filter
     * @param values The list of multiple possible values for given attribute.
     * @param operator 'AND' or 'OR' operator for values
     *
     * @return  A reference to the current Request object
     */
    fun filter(attribute: String, values: List<String>, operator: Operator): WidgetRequest {
        var str = ""
        if (values.size > 1) {
            //attribute:("value 1" OR "value 2")
            for ((index, value) in values.withIndex()) {
                str += "\"${value}\""
                if (index != values.size - 1) {
                    if (operator == Operator.OR) {
                        str += " OR "
                    } else if (operator == Operator.AND) {
                        str += " AND "
                    }
                }
            }
        } else {
            str += "\"${values[0]}\""
        }

        return add(ApiConstants.FILTER, "$attribute:($str)")
    }

    /**
     * Method to set facetPrefix for Widget APIs
     * The facet.prefix parameter limits faceting to terms that start with the specified string prefix.
     *
     * @param facetName The name of the facet
     * @param prefixValue value for facet prefix
     *
     * @return  A reference to the current Request object
     */
    fun facetPrefix(facetName: String, prefixValue: String): WidgetRequest {
        return set("facet.prefix", "$facetName:$prefixValue")
    }

    /**
     * Method to set url
     *
     * @param value The absolute URL of the page where the request is initiated. Do not use a relative URL.
     *
     * @return  A reference to the current Request object
     */
    fun url(value: String): WidgetRequest {
        return set(ApiConstants.URL, value)
    }

    /**
     * Method to set image id for Visual search
     *
     * @param value image id
     *
     * @return  A reference to the current Request object
     */
    fun imageId(value: String): WidgetRequest {
        return set(ApiConstants.IMAGE_ID, value)
    }
}