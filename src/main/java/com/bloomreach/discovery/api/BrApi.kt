/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api

import com.bloomreach.discovery.api.listener.BrApiCompletionListener
import com.bloomreach.discovery.api.network.ApiProcessor
import com.bloomreach.discovery.api.request.*

/**
 * BrApi Singleton class holds method to initiate BrApiRequest object and API calls methods
 */
object BrApi {
    private val TAG: String = BrApi.javaClass.simpleName
    private val apiProcessor = ApiProcessor()
    lateinit var brApiRequest: BrApiRequest

    /**
     * Initialise BrApi class with BrApiRequest object
     * @param brApiRequest BrApiRequest object defined for initialisation
     */
    public fun init(brApiRequest: BrApiRequest) {
        BrApi.brApiRequest = brApiRequest
    }

    /**
     * Method for calling Product Search API request
     * @param productSearchRequest Request Object required for Product Search API
     * @param brApiCompletionListener Callback listener
     */
    fun productSearchApi(productSearchRequest: ProductSearchRequest, brApiCompletionListener: BrApiCompletionListener) {
        apiProcessor.processCoreApi(productSearchRequest.getMap(), brApiCompletionListener)
    }

    /**
     * Method for calling Category Search API request
     * @param categorySearchRequest Request Object required for Category Search API
     * @param brApiCompletionListener Callback listener
     */
    fun categorySearchApi(categorySearchRequest: CategorySearchRequest, brApiCompletionListener: BrApiCompletionListener) {
        apiProcessor.processCoreApi(categorySearchRequest.getMap(), brApiCompletionListener)
    }

    /**
     * Method for calling Content API request
     * @param contentSearchRequest Request Object required for Content Search API
     * @param brApiCompletionListener Callback listener
     */
    fun contentSearchApi(contentSearchRequest: ContentSearchRequest, brApiCompletionListener: BrApiCompletionListener) {
        apiProcessor.processCoreApi(contentSearchRequest.getMap(), brApiCompletionListener)
    }

    /**
     * Method for calling BestSeller API request
     * @param bestSellerRequest Request Object required for Content Search API
     * @param brApiCompletionListener Callback listener
     */
    fun bestSellerApi(bestSellerRequest: BestSellerRequest, brApiCompletionListener: BrApiCompletionListener) {
        apiProcessor.processCoreApi(bestSellerRequest.getMap(), brApiCompletionListener)
    }

    /**
     * Method for calling Suggest API request
     * @param autosuggestRequest Request Object required for Content Search API
     * @param brApiCompletionListener Callback listener
     */
    fun autoSuggestApi(autosuggestRequest: AutosuggestRequest, brApiCompletionListener: BrApiCompletionListener) {
        apiProcessor.processSuggestApi(autosuggestRequest.getMap(), brApiCompletionListener)
    }

    /* ========= WIDGET API=== */

    /**
     * Method for calling Recommendation Widget API where apiType can be specified
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param apiType the type of Recommendation Widget API. This is the widgetType path parameter
     * @param widgetRequest request Object required for Global Recommendation Widget API
     *
     * @param brApiCompletionListener Callback listener
     */
    fun recAndPathwaysWidgetApi(widgetId: String, apiType:WidgetApiType, widgetRequest: WidgetRequest, brApiCompletionListener: BrApiCompletionListener) {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }

        recAndPathwaysWidgetApi(widgetId, apiType.value, widgetRequest, brApiCompletionListener)
    }

    /**
     * Method for calling Recommendation Widget API where apiType can be specified
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param apiType the type of Recommendation Widget API. This is the widgetType path parameter
     * @param widgetRequest request Object required for Global Recommendation Widget API
     *
     * @param brApiCompletionListener Callback listener
     */
    fun recAndPathwaysWidgetApi(widgetId: String,
                                apiType:String, widgetRequest: WidgetRequest, brApiCompletionListener: BrApiCompletionListener) {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        apiProcessor.processRecsAndPathwaysApi(widgetId, apiType, widgetRequest.getMap(), brApiCompletionListener)
    }

    /**
     * Method for calling Item-based Recommendation Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Item-based Recommendation Widget API
     *
     * @param brApiCompletionListener Callback listener
     */
    fun itemBasedRecommendationWidgetApi(widgetId: String, widgetRequest: WidgetRequest,  brApiCompletionListener: BrApiCompletionListener) {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        recAndPathwaysWidgetApi(widgetId, WidgetApiType.ITEM.value, widgetRequest, brApiCompletionListener)
    }

    /**
     * Method for calling Category-based Recommendation Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Category-based Recommendation Widget API
     *
     * @param brApiCompletionListener Callback listener
     */
    fun categoryBasedWidgetApi(widgetId: String, widgetRequest: WidgetRequest,  brApiCompletionListener: BrApiCompletionListener) {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        recAndPathwaysWidgetApi(widgetId, WidgetApiType.CATEGORY.value, widgetRequest, brApiCompletionListener)
    }

    /**
     * Method for calling Keyword-based Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Keyword-based Recommendation Widget API
     * @param brApiCompletionListener Callback listener
     */
    fun keywordBasedWidgetApi(widgetId: String, widgetRequest: WidgetRequest,  brApiCompletionListener: BrApiCompletionListener) {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        recAndPathwaysWidgetApi(widgetId, WidgetApiType.KEYWORD.value, widgetRequest, brApiCompletionListener)
    }


    /**
     * Method for calling Personalization-based Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Personalization-based Recommendation Widget API
     *
     * @param brApiCompletionListener Callback listener
     */

    fun personalizationBasedWidgetApi(widgetId: String, widgetRequest: WidgetRequest,  brApiCompletionListener: BrApiCompletionListener) {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        recAndPathwaysWidgetApi(widgetId, WidgetApiType.PERSONALIZED.value, widgetRequest, brApiCompletionListener)
    }

    /**
     * Method for calling Global Recommendation Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Global Recommendation Widget API
     *
     * @param brApiCompletionListener Callback listener
     */
    fun globalRecommendationWidgetApi(widgetId: String, widgetRequest: WidgetRequest,  brApiCompletionListener: BrApiCompletionListener) {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        recAndPathwaysWidgetApi(widgetId, WidgetApiType.GLOBAL.value, widgetRequest, brApiCompletionListener)
    }
}