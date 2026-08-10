/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

import com.bloomreach.discovery.api.ApiConstants
import com.bloomreach.discovery.api.ApiConstants.DEFAULT_ROWS
import com.bloomreach.discovery.api.ApiConstants.DEFAULT_START
import com.bloomreach.discovery.api.ApiConstants.FL
import java.util.stream.Collectors

/**
 *  This class is base class for Search APIs request it provides parameters to be sent with Search APIs
 */
sealed class SearchRequest<T>() : RequestMap<T>() {

    // add default parameters required for search API
    init {
        rows(DEFAULT_ROWS)
        start(DEFAULT_START)
    }

    /**
     * Method to set rows Search APIs
     *
     * @param rows The number of matching items to return per results page in the API response. The maximum value is 200.
     *
     * @return  A reference to the current Request object
     */
    fun rows(rows: Int): T {
        return set(ApiConstants.ROWS, rows.toString())
    }

    /**
     * Method to set start Search APIs
     *
     * @param start The number of the first item on a page of results. For example, the first item on the first page is 0, making the start value also 0.
     *
     * @return  A reference to the current Request object
     */
    fun start(start: Int): T {
        return set(ApiConstants.START, start.toString())
    }

    /**
     * Method to set search term for Search APIs
     *
     * @param q  Query key for Searching
     *
     * @return  A reference to the current Request object
     */
    fun searchTerm(q: String): T {
        return set(ApiConstants.SEARCH_TERM, q)
    }

    /**
     * Method to set Field List for Search APIs
     *
     * @param value The comma separated attributes that you want returned in your API response, such as product IDs and prices.
     *
     * @return  A reference to the current Request object
     */
    fun fl(value: String): T {
        if (value.isEmpty()) {
            throw IllegalArgumentException("")
        }
        return set(FL, value)
    }

    /**
     * Method to set Field List for Search APIs
     *
     * @param values The attributes list that you want returned in your API response, such as product IDs and prices.
     *
     * @return  A reference to the current Request object
     */
    fun fl(values: List<String>): T {
        var flString = ""
        if (values.isNullOrEmpty()) {
            throw IllegalArgumentException()
        } else if (values.size == 1) {
            flString = values[0]
        } else if (values.size > 1) {
            flString = values.joinToString(",")
        }
        return fl(flString)
    }

    /**
     * Method to set Field List for Search APIs
     *
     * @param values The attributes array that you want returned in your API response, such as product IDs and prices.
     *
     * @return  A reference to the current Request object
     */
    fun fl(values: Array<String>): T {
        var flString = ""
        if (values.isNullOrEmpty()) {
            throw IllegalArgumentException()
        } else if (values.size == 1) {
            flString = values[0]
        } else if (values.size > 1) {
            flString = values.joinToString(",")
        }
        return fl(flString)
    }

    /**
     * Method to set sort parameter. You can alter the sequence in which products are
     * displayed by passing the sort parameter.
     *
     * @param value Formatted value for sort parameter. 'price+asc'
     *
     * @return  A reference to the current Request object
     */
    fun sort(value: String?): T {
        val newValue = value?.replace("+", " ")
        return set(ApiConstants.SORT, newValue)
    }

    /**
     * Method to set sort parameter. You can alter the sequence in which products are
     * displayed by passing the sort parameter.
     *
     * @param sort sort object contains value for paramter on which sorting is to be done and SortOrder specifies the Order Asc or Desc
     *
     * @return  A reference to the current Request object
     */
    fun sort(sort: Sort): T {
        return sort(sortString(sort))
    }

    /**
     * Method to set sort parameter. You can alter the sequence in which products are
     * displayed by passing the sort parameter.
     *
     * @param values list of sort objects contains value for paramter on which sorting is to be done and SortOrder specifies the Order Asc or Desc
     *
     * @return  A reference to the current Request object
     */
    fun sort(values: List<Sort>?): T {
        var sortString: String? = null
        if (values.isNullOrEmpty()) {
            sortString = null
        } else if (values.size == 1) {
            sortString = sortString(values[0]!!)
        } else if (values.size > 1) {
            sortString = sortString(values)
        }
        return sort(sortString)
    }

    /**
     * Method to format Sort object in required String format
     *
     * @param sort object
     *
     * @return  Formatted string for sort parameter
     */
    private fun sortString(sort: Sort):String {
        return "${sort.value} ${sort.order.value}"
    }

    /**
     * Method to format List of Sort object in required String format
     *
     * @param sortList list of sort object
     *
     * @return  Formatted string for sort parameter
     */
    private fun sortString(sortList: List<Sort>): String {
        return sortList.stream()
            .map { sort -> sortString(sort) }
            .collect(Collectors.joining(","))
    }

    /**
     * Method to set sort parameter. You can alter the sequence in which products are
     * displayed by passing the sort parameter.
     *
     * @param values Array of sort string
     *
     * @return  A reference to the current Request object
     */
    fun sort(values: Array<String>?): T {
        var sortString: String? = null
        if (values.isNullOrEmpty()) {
            sortString = null
        } else if (values.size == 1) {
            sortString = values[0]
        } else if (values.size > 1) {
            sortString = values.joinToString(",")
        }
        return sort(sortString)
    }

    /**
     * Method to set facetPrefix for Search APIs.
     * The facet.prefix parameter limits faceting to terms that start with the specified string prefix.
     *
     * @param facetName The name of the facet
     * @param prefixValue value for facet prefix
     *
     * @return  A reference to the current Request object
     */
    fun facetPrefix(facetName: String, prefixValue: String): T {
        val key = "f.${facetName}.facet.prefix"
        return set(key, prefixValue)
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
    fun facetPrefixWidget(facetName: String, prefixValue: String): T {
        return set("facet.prefix", "$facetName:$prefixValue")
    }

    /**
     * Method to set fq
     *  The fq parameter is an optional parameter that you can add to an API request to filter the results.
     *
     * @param value The formatted value to be passed to fq parameter
     *
     * @return  A reference to the current Request object
     */
    fun fq(value: String): T {
        return add(ApiConstants.FQ, value)
    }

    /**
     * Method to set fq with attribute and its single value
     * The fq parameter is an optional parameter that you can add to an API request to filter the results.
     *
     * @param attribute The attribute for fq
     * @param value The value of the attribute
     *
     * @return  A reference to the current Request object
     */
    fun fq(attribute: String, value: String): T {
        val fqValue = "$attribute:\"${value}\""
        return fq(fqValue)
    }

    /**
     * Method to set fq with attribute and its multiple value
     * The fq parameter is an optional parameter that you can add to an API request to filter the results.
     *
     * @param attribute The attribute for fq
     * @param values The list of multiple possible values for given attribute.
     *
     * @return  A reference to the current Request object
     */
    fun fq(attribute: String, values: List<String>): T {
        var str = ""
        if (values.size > 1) {
            for ((index, value) in values.withIndex()) {
                str += "\"${value}\""
                if (index != values.size - 1) {
                    str += " OR "
                }
            }
        } else {
            str += values[0]
        }
        return fq(attribute, str)
    }

    /**
     * Method to set stats.field
     * The stats.field allows you to display the maximum and minimum values of any numeric field in your data set for a user query.
     *
     * @param value The formatted stats.field value
     *
     * @return  A reference to the current Request object
     */
    fun statsField(value: String?): T {
        return set(ApiConstants.STATS_FIELD, value)
    }

    /**
     * Method to set stats.field
     * The stats.field allows you to display the maximum and minimum values of any numeric field in your data set for a user query.
     *
     * @param values The list of stats.field values
     *
     * @return  A reference to the current Request object
     */
    fun statsField(values: List<String>): T {
        var sfString: String? = null
        if (values.isNullOrEmpty()) {
            sfString = null
        } else if (values.size == 1) {
            sfString = values[0]
        } else if (!values.isNullOrEmpty() && values.size > 1) {
            sfString = values.joinToString(",")
        }
        return statsField(sfString)
    }

    /**
     * Method to set stats.field
     * The stats.field allows you to display the maximum and minimum values of any numeric field in your data set for a user query.
     *
     * @param values The array of stats.field values
     *
     * @return  A reference to the current Request object
     */
    fun statsField(values: Array<String>): T {
        var sfString: String? = null
        if (values.isNullOrEmpty()) {
            sfString = null
        } else if (values.size == 1) {
            sfString = values[0]
        } else if (!values.isNullOrEmpty() && values.size > 1) {
            sfString = values.joinToString(",")
        }
        return statsField(sfString)
    }

    fun efq(value: String): T {
        return set(ApiConstants.EFQ, value)
    }

    fun efq(attribute: String, value: String): T {
        return efq("$attribute:(\"${value}\")")
    }

    fun efq(attribute: String, value: String, isNot: Boolean): T {
        return if(isNot) {
            efq("-$attribute:(\"${value}\")")
        } else {
            efq("$attribute:(\"${value}\")")
        }
    }

    /**
     * Method to set efq with attribute and its multiple values
     *
     * @param attribute The attribute for efq
     * @param values The list of multiple possible values for given attribute.
     * @param operator 'AND' or 'OR' operator for values
     *
     * @return  A reference to the current Request object
     */
    fun efq(attribute: String, values: List<String>, operator: Operator): T {
        var str = ""
        if (values.size > 1) {
            //attribute:("value 1" OR "value 2")
            for ((index, value) in values.withIndex()) {
                str += "\"${value}\"" //TODO
                if (index != values.size - 1) {
                    if (operator == Operator.OR) {
                        str += " OR "
                    } else if (operator == Operator.AND) {
                        str += " AND "
                    }
                }
            }
        } else {
            str += values[0]
        }
        return efq(attribute, str)
    }

    /**
     * Method to set efq with multiple attribute and  values
     *
     * @param values The map of multiple possible attributes and its values.
     * @param operator 'AND' or 'OR' operator for attributes
     *
     * @return  A reference to the current Request object
     */
    fun efq(values: Map<String, String>, operator: Operator): T {
        var formattedStr = ""
        //attribute1:("value") OR attribute2:("value")
        if (values.size > 1) {
            formattedStr = if (operator == Operator.OR) {
                values.entries.stream()
                    .map { e -> "${e.key}:(\"${e.value}\")" }
                    .collect(Collectors.joining(" OR "))
            } else {
                values.entries.stream()
                    .map { e -> "${e.key}:(\"${e.value}\")" }
                    .collect(Collectors.joining(" AND "))
            }
        }
        return efq(formattedStr)
    }

    /**
     * Method to set facet.range parameter
     * Use the facet.range parameter to include ranged facets
     *
     * @param value value for the facet range
     *
     * @return  A reference to the current Request object
     */
    fun facetRange(value: String?): T {
        return set(ApiConstants.FACET_RANGE, value)
    }

    /**
     * Method to set list of facet.range parameter
     * Use the facet.range parameter to include ranged facets
     *
     * @param values list for the facet range
     *
     * @return  A reference to the current Request object
     */
    fun facetRange(values: List<String>): T {
        var frString: String? = null
        if (values.isNullOrEmpty()) {
            frString = null
        } else if (values.size == 1) {
            frString = values[0]
        } else if (values.size > 1) {
            frString = values.joinToString(",")
        }
        return facetRange(frString)
    }

    /**
     * BOPIS-specific parameter to specify the end-customer's latitude-longitude.
     *
     * @param value value for lat long in format 'lat,long'
     *
     * @return  A reference to the current Request object
     */
    fun latLong(value: String?): T {
        return set(ApiConstants.LAT_LONG, value)
    }

    /**
     * Method to set View Id
     *
     * @param value A unique identifier for a specific view of your product catalog.
     *
     * @return  A reference to the current Request object
     */
    fun viewId(value: String?): T {
        return set(ApiConstants.VIEW_ID, value)
    }

    /**
     * Method to set user id of the customer
     *
     * @param value The universal customer ID of the user.
     *
     * @return  A reference to the current Request object
     */
    fun userId(value: String?): T {
        return set(ApiConstants.USER_ID, value)
    }

    /**
     * Method to set widget Id, The widget_id provided in the Dashboard for the Dynamic Widgets
     * feature, which is used to provided curated results.
     *
     * @param value value for widget id
     *
     * @return  A reference to the current Request object
     */
    fun widgetId(value: String?): T {
        return set(ApiConstants.WIDGET_ID, value)
    }

    /**
     * Method to set title
     *
     * @param value The title or name of the product.
     *
     * @return  A reference to the current Request object
     */
    fun title(value: String?): T {
        return set(ApiConstants.TITLE, value)
    }

    /**
     * Method to set url
     *
     * @param value The title or name of the product.
     *
     * @return  A reference to the current Request object
     */
    fun url(value: String): T {
        return set(ApiConstants.URL, value)
    }

    /**
     * Method to Facet Version
     *
     * @param value facet version eg: '3.0'
     *
     * @return  A reference to the current Request object
     */
    fun facetVersion(value: String): T {
        return set(ApiConstants.FACET_VERSION, value)
    }

    /**
     * Method to Hard Boost
     *
     * @param value of Hard Boost eg: pid:“pid1” OR “pid2”
     *
     * @return  A reference to the current Request object
     */
    fun hardBoost(value: String): T {
        return set(ApiConstants.BOOST, value)
    }


    /**
     * Method to Hard Bury
     *
     * @param value of Hard Bury eg: pid:“pid1” OR “pid2”
     *
     * @return  A reference to the current Request object
     */
    fun hardBury(value: String): T {
        return set(ApiConstants.BURY, value)
    }

    /**
     * Method to LOCK POSITION
     *
     * @param value of Lock eg: pid:”pid1”#3 OR ”pid2”#10
     *
     * @return  A reference to the current Request object
     */
    fun lock(value: String): T {
        return set(ApiConstants.LOCK, value)
    }

    /**
     * Method to addToRecall
     *
     * @param value of add_to_recall eg: pid:“pid1” OR “pid2”
     *
     * @return  A reference to the current Request object
     */
    fun addToRecall(value: String): T {
        return set(ApiConstants.ADD_TO_RECALL, value)
    }

    /**
     * Method to SOFT BOOST
     *
     * @param value of soft_boost eg: ”brand”:”Brand1” OR “Brand2”
     *
     * @return  A reference to the current Request object
     */
    fun softBoost(value: String): T {
        return set(ApiConstants.SOFT_BOOST, value)
    }

    /**
     * Method to SOFT Bury
     *
     * @param value of soft_bury eg: ”brand”:”Brand1” OR “Brand2”
     *
     * @return  A reference to the current Request object
     */
    fun softBury(value: String): T {
        return set(ApiConstants.SOFT_BURY, value)
    }

    /**
     * Method to include
     *
     * @param value of include eg: ”brand”:”Brand1” OR “Brand2”
     *
     * @return  A reference to the current Request object
     */
    fun include(value: String): T {
        return set(ApiConstants.INCLUDE, value)
    }

    /**
     * Method to exclude
     *
     * @param value of exclude eg: brand:”Brand1” OR “Brand2”
     *
     * @return  A reference to the current Request object
     */
    fun exclude(value: String): T {
        return set(ApiConstants.EXCLUDE, value)
    }

    /**
     * Method to Apply Loomi Search+ API Controls
     *
     * @param value ENUM of type SearchMode.
     * HYBRID for Loomi Search+ mode or STANDARD for Keyword search mode
     *
     * @return  A reference to the current Request object
     */
    fun searchMode(value: SearchMode): T {
        return set(ApiConstants.QUERY_SEARCH_MODE, value.value)
    }

    /**
     * Method to Apply Vector Search Temperature
     *
     * @param value ENUM of type VectorSearchTemperature
     * standard For a wider recall or high For a compact and very precise recall
     *
     *
     * @return  A reference to the current Request object
     */
    fun vectorSearchTemperature(value: VectorSearchTemperature): T {
        return set(ApiConstants.VECTOR_SEARCH_TEMPERATURE, value.value)
    }
}