/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api

/**
 * API module constants including request parameters
 */
internal object ApiConstants {

    const val REQUEST_TYPE = "request_type"
    const val SEARCH_TYPE = "search_type"

    const val REQUEST_TYPE_SEARCH = "search"
    const val REQUEST_TYPE_SUGGEST = "suggest"

    const val SEARCH_TYPE_KEYWORD = "keyword"
    const val SEARCH_TYPE_CATEGORY = "category"
    const val SEARCH_TYPE_BESTSELLER = "bestseller"

    const val ROWS = "rows"
    const val DEFAULT_ROWS = 10

    const val START = "start"
    const val DEFAULT_START = 0

    const val SEARCH_TERM = "q"
    const val FL = "fl"
    const val FQ = "fq"
    const val SORT = "sort"
    const val STATS_FIELD = "stats.field"
    const val EFQ = "efq"
    const val LAT_LONG = "ll"
    const val FACET_RANGE = "facet.range"

    const val USER_ID = "user_id"
    const val VIEW_ID = "view_id"
    const val WIDGET_ID = "widget_id"

    const val ITEM_IDS = "item_ids"
    const val CAT_ID = "cat_id"
    const val QUERY = "query"
    const val CATALOG_NAME = "catalog_name"
    const val TITLE = "title"
    const val URL = "url"
    const val CATALOG_VIEWS = "catalog_views"
    const val USER_AGENT = "user_agent"
    const val CONTEXT_ID = "context_id"
    const val FIELDS = "fields"
    const val FILTER_FACET = "filter_facet"
    const val FACET = "facet"
    const val FILTER = "filter"
    const val IMAGE_ID = "image_id"
    const val FACET_VERSION = "facet.version"

    const val DEFAULT_FACET_FLAG = false

    const val CATEGORY_TYPE = "category_type"
    const val CATEGORY_TYPE_DYNAMIC = "dynamic"

}