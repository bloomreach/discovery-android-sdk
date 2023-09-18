/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.network

import android.net.Uri
import com.bloomreach.discovery.api.BrApi
import com.bloomreach.discovery.api.listener.BrApiCompletionListener
import com.bloomreach.discovery.api.model.BrApiError
import com.bloomreach.discovery.api.model.Env
import com.bloomreach.discovery.api.model.core.CoreResponse
import com.bloomreach.discovery.api.model.rp.RecsAndPathwaysResponse
import com.bloomreach.discovery.api.model.suggest.SuggestResponse
import com.bloomreach.discovery.pixel.processpixel.FormatterUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

/**
 * Class for adding global parameters to the request, processing all types of API call and providing callback once API returns
 */
internal class ApiProcessor {

    private val CORE_API_PATH = "api/v1/core/"
    private val SUGGEST_API_PATH = "api/v2/suggest"
    private val WIDGET_API_PATH = "api/v2/widgets/"
    private val SCEHEME = "https"

    /**
     * Method to format Core API parameters, execute the API and invoke the callback with appropriate result
     * @param params Map of request parameters to be sent with the request
     * @param brApiCompletionListener Interface object to provide success or failure callback
     */
    fun processCoreApi(
        params: MutableMap<String, Any?>,
        brApiCompletionListener: BrApiCompletionListener
    ) {
        var uriBuilder = FormatterUtils.mapToUriBuilderForApi(params)
        //add global request parameters
        uriBuilder = addGlobalQuery(uriBuilder)
        // append base endpoint for API call
        addBaseUrlForCoreApi(uriBuilder)
        //perform API
        CoroutineScope(Dispatchers.IO).launch {
            val client = RestClientApi()

            val result = client.doApiCall(URL(uriBuilder.build().toString()), ApiType.CORE)
            if (result is CoreResponse)
            //invoke success callback
                brApiCompletionListener.onBrApiSuccess(result)
            else if (result is BrApiError)
            //invoke failure callback
                brApiCompletionListener.onBrApiFailure(result)
        }
    }

    /**
     * Method to format Suggest API parameters, execute the API and invoke the callback with appropriate result
     * @param params Map of request parameters to be sent with the request
     * @param brApiCompletionListener Interface object to provide success or failure callback
     */
    fun processSuggestApi(
        params: MutableMap<String, Any?>,
        brApiCompletionListener: BrApiCompletionListener
    ) {
        val uriBuilder = FormatterUtils.mapToUriBuilderForApi(params)
        //add global request parameters
        addGlobalQuery(uriBuilder)
        // append base endpoint for API call
        addBaseUrlForSuggestApi(uriBuilder)
        //perform API
        CoroutineScope(Dispatchers.IO).launch {
            val client = RestClientApi()
            val result = client.doApiCall(URL(uriBuilder.build().toString()), ApiType.SUGGEST)
            if (result is SuggestResponse)
            //invoke success callback
                brApiCompletionListener.onBrApiSuccess(result)
            else if (result is BrApiError)
            //invoke failure callback
                brApiCompletionListener.onBrApiFailure(result)
        }
    }

    /**
     * Method to format Pathways APIs parameters, execute the API and invoke the callback with appropriate result
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetType Type of widget
     * @param params Map of request parameters to be sent with the request
     * @param brApiCompletionListener Interface object to provide success or failure callback
     */
    fun processRecsAndPathwaysApi(
        widgetId: String,
        widgetType: String,
        params: MutableMap<String, Any?>,
        brApiCompletionListener: BrApiCompletionListener
    ) {
        val uriBuilder = FormatterUtils.mapToUriBuilderForApi(params)
        //add global request parameters
        addGlobalQuery(uriBuilder)
        // append base endpoint for Pixel call
        addBaseUrlForPathwaysApi(uriBuilder, widgetType, widgetId)
        //perform API
        CoroutineScope(Dispatchers.IO).launch {
            val client = RestClientApi()
            val result = client.doApiCall(URL(uriBuilder.build().toString()), ApiType.PATHWAYS)
            if (result is RecsAndPathwaysResponse)
            //invoke success callback
                brApiCompletionListener.onBrApiSuccess(result)
            else if (result is BrApiError)
            //invoke failure callback
                brApiCompletionListener.onBrApiFailure(result)
        }
    }

    /**
     * Method to add global request parameters to Uri Builder
     * @param uriBuilder The Uri.Builder where the global request parameters will be added in required format
     */
    fun addGlobalQuery(uriBuilder: Uri.Builder): Uri.Builder {
        uriBuilder.appendQueryParameter("account_id", BrApi.brApiRequest.accountId)
        uriBuilder.appendQueryParameter("auth_key", BrApi.brApiRequest.authKey)
        uriBuilder.appendQueryParameter("domain_key", BrApi.brApiRequest.domainKey)
        uriBuilder.appendQueryParameter("request_id", FormatterUtils.generateRand())
        uriBuilder.appendQueryParameter(
            "_br_uid_2",
            FormatterUtils.formatCookieValue(
                BrApi.brApiRequest.uuid,
                BrApi.brApiRequest.visitorType
            )
        )
        uriBuilder.appendQueryParameter("ref_url", "")
        if (!BrApi.brApiRequest.userId.isNullOrEmpty()) {
            uriBuilder.appendQueryParameter("user_id", BrApi.brApiRequest.userId)
        }
        return uriBuilder
    }

    /**
     * Method to generate Base EndPoint Url for Stage Env
     *
     * @param uriBuilder The Uri.Builder the Base Url Endpoint will be set
     *
     */
    fun addBaseUrlForCoreApi(uriBuilder: Uri.Builder) {
        uriBuilder.scheme(SCEHEME)
        //check for env if stage or prod
        when (BrApi.brApiRequest.environment) {
            Env.STAGE -> uriBuilder.authority("staging-core.dxpapi.com")
            Env.PROD -> uriBuilder.authority("core.dxpapi.com")
        }
        uriBuilder.appendEncodedPath(CORE_API_PATH)
    }

    /**
     * Method to generate Base EndPoint Url for Stage Env
     *
     * @param uriBuilder The Uri.Builder the Base Url Endpoint will be set
     *
     */
    fun addBaseUrlForSuggestApi(uriBuilder: Uri.Builder) {
        uriBuilder.scheme(SCEHEME)
        //check for env if stage or prod
        when (BrApi.brApiRequest.environment) {
            Env.STAGE -> uriBuilder.authority("staging-suggest.dxpapi.com")
            Env.PROD -> uriBuilder.authority("suggest.dxpapi.com")
        }
        uriBuilder.appendEncodedPath(SUGGEST_API_PATH)
    }

    /**
     * Method to generate Base EndPoint Url for Stage Env
     *
     * @param uriBuilder The Uri.Builder the Base Url Endpoint will be set
     * @param widgetType Type of widget
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     *
     */
    fun addBaseUrlForPathwaysApi(uriBuilder: Uri.Builder, widgetType: String, widgetId: String) {
        uriBuilder.scheme(SCEHEME)
        //check for env if stage or prod
        when (BrApi.brApiRequest.environment) {
            Env.STAGE -> uriBuilder.authority("pathways-staging.dxpapi.com")
            Env.PROD -> uriBuilder.authority("pathways.dxpapi.com")
        }
        uriBuilder.appendEncodedPath("$WIDGET_API_PATH$widgetType/$widgetId")
    }
}