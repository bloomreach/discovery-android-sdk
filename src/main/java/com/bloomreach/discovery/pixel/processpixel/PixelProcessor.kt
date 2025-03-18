/*
 * Copyright 2022 Bloomreach
 */
package com.bloomreach.discovery.pixel.processpixel

import android.os.Build
import androidx.annotation.RequiresApi
import com.bloomreach.discovery.BuildConfig
import com.bloomreach.discovery.pixel.PixelTracker
import com.bloomreach.discovery.pixel.model.PageType
import com.bloomreach.discovery.pixel.model.PixelObject
import com.bloomreach.discovery.pixel.model.PixelType
import com.bloomreach.discovery.pixel.network.RestClient
import com.bloomreach.discovery.pixel.processpixel.FormatterUtils.generateRand
import com.bloomreach.discovery.pixel.submitpixel.ListenableQueue
import com.bloomreach.discovery.pixel.submitpixel.PixelQueue
import com.bloomreach.discovery.pixel.validator.PixelValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Class for processing all the pixel and converting it in required query parameter string
 */
internal class PixelProcessor {

    //private val pixelJob: PixelJob = PixelJob()
    private val restClient: RestClient = RestClient()
    private val pixelValidator: PixelValidator = PixelValidator()
    private val pageViewPixelFormatter = PageViewPixelFormatter()

    init {
        PixelQueue.pixels.registerListener(object :
            ListenableQueue.Listener<MutableMap<String, String?>> {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onElementAdded(element: MutableMap<String, String?>) {
                performApi()
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onElementRemoved(element: MutableMap<String, String?>?) {
                performApi()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun performApi() {
        val nextElementFromQueue = PixelQueue.get()
        if (nextElementFromQueue != null) {
            val uriBuilder = FormatterUtils.mapToUriBuilder(nextElementFromQueue)
            CoroutineScope(Dispatchers.IO).launch {
                //API call
                val response = restClient.submitPixel(uriBuilder)
                PixelQueue.remove()
            }
        }
    }

    /**
     * Method to process Pixel triggered by Merchant app and convert it in required query parameter string
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     */
    fun processPixel(pixelObject: PixelObject) {
        val queryMap = mutableMapOf<String, String?>()

        // process generic value
        prepareGlobalQuery(pixelObject, queryMap)

        when (pixelObject.type) {
            PixelType.PAGE_VIEW -> {
                processPageViewPixel(pixelObject, queryMap)
            }
            PixelType.EVENT -> {
                processEventPixel(pixelObject, queryMap)
            }
        }

        // Validate Pixel only when in DEBUG mode
        if (PixelTracker.brPixel.debugMode) {
            pixelValidator.validatePixel(queryMap)
        }

        // add the processed Map to Queue for further process
        PixelQueue.add(queryMap)
    }

    fun processPixel(queryMap: MutableMap<String, String?>) {

        queryMap.put("acct_id", PixelTracker.brPixel.accountId)

        queryMap.put(
            "cookie2", FormatterUtils.formatCookieValue(
                PixelTracker.brPixel.uuid,
                PixelTracker.brPixel.visitorType
            )
        )

        queryMap.put("rand", generateRand())

        if (PixelTracker.brPixel.testData) {
            queryMap.put("test_data", PixelTracker.brPixel.testData.toString())
        }

        queryMap.put(
            "url", FormatterUtils.formatUrl(
                PixelTracker.brPixel.baseUrl,
                queryMap["ptype"] ?: "",
                queryMap["title"] ?: ""
            )
        )

        // customer user id
        if (!PixelTracker.brPixel.userId.isNullOrEmpty()) {
            queryMap.put("user_id", PixelTracker.brPixel.userId)
        }

        // customer tier
        if (!PixelTracker.brPixel.customerTier.isNullOrEmpty()) {
            queryMap.put("customer_tier", PixelTracker.brPixel.customerTier)
        }

        // customer country  present
        if (!PixelTracker.brPixel.customerCountry.isNullOrEmpty()) {
            queryMap.put("customer_country", PixelTracker.brPixel.customerCountry)
        }

        // customer geo  present
        if (!PixelTracker.brPixel.customerGeo.isNullOrEmpty()) {
            queryMap.put("customer_geo", PixelTracker.brPixel.customerGeo)
        }

        // customer profile  present
        if (!PixelTracker.brPixel.customerProfile.isNullOrEmpty()) {
            queryMap.put("customer_profile", PixelTracker.brPixel.customerProfile)
        }

        // Validate Pixel only when in DEBUG mode
        if (PixelTracker.brPixel.debugMode) {
            pixelValidator.validatePixel(queryMap)
        }

        //viewId
        if (!PixelTracker.brPixel.viewId.isNullOrEmpty()) {
            queryMap.put("view_id", PixelTracker.brPixel.viewId)
        }

        //Currency
        if (!PixelTracker.brPixel.currency.isNullOrEmpty()) {
            queryMap.put("currency", PixelTracker.brPixel.currency)
        }

        //DomainKey
        if (!PixelTracker.brPixel.domainKey.isNullOrEmpty()) {
            queryMap.put("domain_key", PixelTracker.brPixel.domainKey)
        }

        //debug
        if (PixelTracker.brPixel.debugMode) {
            queryMap.put("debug", PixelTracker.brPixel.debugMode.toString())
        }

        //abtest
        if (!PixelTracker.brPixel.abTest.isNullOrEmpty()) {
            queryMap.put("abtest", PixelTracker.brPixel.abTest)
        }

        // add the processed Map to Queue for further process
        PixelQueue.add(queryMap)
    }

    /**
     * Method to process only Pixel View Pixel
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @return String value in required format
     */
    private fun processPageViewPixel(
        pixelObject: PixelObject,
        queryMap: MutableMap<String, String?>
    ): MutableMap<String, String?> {
        // do processing based on pType for each PageView Pixels
        when (pixelObject.pType) {
            PageType.HOME_PAGE -> {
                return queryMap
            }

            PageType.PRODUCT_PAGE -> {
                return pageViewPixelFormatter.prepareProductPageViewQuery(pixelObject, queryMap)
            }

            PageType.CONTENT_PAGE -> {
                return pageViewPixelFormatter.prepareContentPageViewQuery(pixelObject, queryMap)
            }

            PageType.SEARCH_PAGE -> {
                return if (pixelObject.catalogs.isNullOrEmpty()) {
                    pageViewPixelFormatter.prepareSearchPageViewQuery(pixelObject, queryMap)
                } else {
                    pageViewPixelFormatter.prepareContentSearchPageViewQuery(
                        pixelObject,
                        queryMap
                    )
                }
            }

            PageType.CATEGORY_PAGE -> {
                return pageViewPixelFormatter.prepareCategoryPageViewQuery(pixelObject, queryMap)
            }

            PageType.CONVERSION -> {
                return pageViewPixelFormatter.prepareConversionPageViewQuery(pixelObject, queryMap)
            }

//            PageType.OTHER_PAGE -> {
//                return pageViewPixelFormatter.prepareConversionPageViewQuery(pixelObject, queryMap)
//            }
            else -> return queryMap
        }
    }

    /**
     * Method to process only Event Pixel
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @param queryMap reference of Map where the values will be added
     * @return Map with values in required format
     */
    private fun processEventPixel(
        pixelObject: PixelObject,
        queryMap: MutableMap<String, String?>
    ): MutableMap<String, String?> {
        queryMap.put("group", pixelObject.group?.group)

        queryMap.put("etype", pixelObject.eType)

        if (!pixelObject.prodId.isNullOrEmpty()) {
            queryMap.put("prod_id", pixelObject.prodId)
        }

        if (!pixelObject.prodName.isNullOrEmpty()) {
            queryMap.put("prod_name", pixelObject.prodName)
        }

        if (!pixelObject.prodSku.isNullOrEmpty()) {
            queryMap.put("sku", pixelObject.prodSku)
        }

        if (!pixelObject.prodCollectionId.isNullOrEmpty()) {
            queryMap.put("prod_collection_id", pixelObject.prodCollectionId)
        }

        if (!pixelObject.searchTerm.isNullOrEmpty()) {
            queryMap.put("q", pixelObject.searchTerm)
        }

        if (!pixelObject.typedTerm.isNullOrEmpty()) {
            queryMap.put("aq", pixelObject.typedTerm)
        }

        if (!pixelObject.catalogs.isNullOrEmpty()) {
            queryMap.put("catalogs", FormatterUtils.formatCatalog(pixelObject.catalogs))
        }

        return queryMap
    }

    /**
     * Method to process Global parameter required for Pixel and convert in required format
     * @param pixelObject internal object which holds data for fields required to generate query parameter String
     * @param queryMap reference of Map where the values will be added
     * @return Map with values in required format
     */
    private fun prepareGlobalQuery(
        pixelObject: PixelObject,
        queryMap: MutableMap<String, String?>
    ): MutableMap<String, String?> {
        queryMap.put("acct_id", PixelTracker.brPixel.accountId)

        queryMap.put(
            "cookie2", FormatterUtils.formatCookieValue(
                PixelTracker.brPixel.uuid,
                PixelTracker.brPixel.visitorType
            )
        )

        queryMap.put("rand", generateRand())

        queryMap.put("type", pixelObject.type.type)

        queryMap.put("ptype", pixelObject.pType.pType)

        if (PixelTracker.brPixel.testData) {
            queryMap.put("test_data", PixelTracker.brPixel.testData.toString())
        }

        queryMap.put("title", pixelObject.title)

        queryMap.put(
            "url", FormatterUtils.formatUrl(
                PixelTracker.brPixel.baseUrl,
                pixelObject.pType.pType,
                pixelObject.title
            )
        )

        queryMap.put("ref", pixelObject.ref)

        // customer user id
        if (!PixelTracker.brPixel.userId.isNullOrEmpty()) {
            queryMap.put("user_id", PixelTracker.brPixel.userId)
        }

        // customer tier
        if (!PixelTracker.brPixel.customerTier.isNullOrEmpty()) {
            queryMap.put("customer_tier", PixelTracker.brPixel.customerTier)
        }

        // customer country  present
        if (!PixelTracker.brPixel.customerCountry.isNullOrEmpty()) {
            queryMap.put("customer_country", PixelTracker.brPixel.customerCountry)
        }

        // customer geo  present
        if (!PixelTracker.brPixel.customerGeo.isNullOrEmpty()) {
            queryMap.put("customer_geo", PixelTracker.brPixel.customerGeo)
        }

        // customer profile  present
        if (!PixelTracker.brPixel.customerProfile.isNullOrEmpty()) {
            queryMap.put("customer_profile", PixelTracker.brPixel.customerProfile)
        }

        //viewId
        if (!PixelTracker.brPixel.viewId.isNullOrEmpty()) {
            queryMap.put("view_id", PixelTracker.brPixel.viewId)
        }

        //Currency
        if (!PixelTracker.brPixel.currency.isNullOrEmpty()) {
            queryMap.put("currency", PixelTracker.brPixel.currency)
        }

        //DomainKey
        if (!PixelTracker.brPixel.domainKey.isNullOrEmpty()) {
            queryMap.put("domain_key", PixelTracker.brPixel.domainKey)
        }

        //debug
        if (PixelTracker.brPixel.debugMode) {
            queryMap.put("debug", PixelTracker.brPixel.debugMode.toString())
        }

        //abtest
        if (!PixelTracker.brPixel.abTest.isNullOrEmpty()) {
            queryMap.put("abtest", PixelTracker.brPixel.abTest)
        }

        return queryMap
    }
}