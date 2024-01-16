/*
 * Copyright 2022 Bloomreach
 */
package com.bloomreach.discovery.pixel

import android.util.Log
import com.bloomreach.discovery.pixel.processpixel.PixelProcessor
import com.bloomreach.discovery.pixel.model.*

/**
 * PixelTracker Singleton class holds all types of Page view and Event Pixels methods
 */
object PixelTracker {

    private val TAG: String = PixelTracker.javaClass.simpleName
    lateinit var brPixel: BrPixel
    private val pixelProcessor: PixelProcessor = PixelProcessor()

    /**
     * Initialise Pixel tracker with BrPixel object
     * @param brPixel BrPixel object defined for initialisation
     */
    fun init(brPixel: BrPixel) {
        this.brPixel = brPixel
    }

    /**
     * Method for sending the Page View Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     */
    fun pageViewPixel(ref: String, title: String, viewId: String? = null) {
        if (this::brPixel.isInitialized) {
            // create pixel object based on input
            val pixelObject = PixelObject(
                type = PixelType.PAGE_VIEW,
                pType = PageType.HOME_PAGE,
                ref = ref,
                title = title
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)

        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Product Page View Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param prodId This is the unique ID which describes a product or product collection.
     * @param prodName The name of the product being viewed.
     * @param sku Unique sku ID that represents the selected variant of this product. If your site does not have SKUs, leave this blank.
     */
    fun productPageViewPixel(
        ref: String,
        title: String,
        prodId: String,
        prodName: String,
        sku: String? = null
    ) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.PAGE_VIEW, pType = PageType.PRODUCT_PAGE,
                ref = ref, title = title, prodId = prodId, prodName = prodName, prodSku = sku
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)

        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Content Page View Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param catalogs List of CatalogItem that are shown in the page. In case the page has multiple
     *                 tabs, only the catalogs of the selected (and visualized) tabs should be included.
     *                 If multiple catalogs are shown in the active page (or tab) all of them should be included.
     * @param itemId Unique ID of the item being shown in the page. This identifier should match
     *               the item_id as specified in the content feed.
     * @param itemName Name or the title of the content page.
     */
    fun contentPageViewPixel(
        ref: String,
        title: String,
        catalogs: List<CatalogItem>,
        itemId: String,
        itemName: String
    ) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.PAGE_VIEW,
                pType = PageType.CONTENT_PAGE,
                ref = ref,
                title = title,
                catalogs = catalogs,
                itemId = itemId,
                itemName = itemName
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Content Search Page View Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param catalogs List of CatalogItem that are shown in the page.
     * @param searchTerm The value of the search query describing the page.
     */
    fun contentSearchPageViewPixel(
        ref: String,
        title: String,
        catalogs: List<CatalogItem>,
        searchTerm: String
    ) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.PAGE_VIEW,
                pType = PageType.SEARCH_PAGE,
                ref = ref,
                title = title,
                catalogs = catalogs,
                searchTerm = searchTerm
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)

        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Category Search Page View Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param categoryId Unique category ID as referred to in the database/catalog. Bloomreach requires the cat_id field to be unique across your site.
     * @param category The bread crumb of the page. Value needs to match the crumb value in your feed. eg: "Home|Clothing|Outerwear"
     */
    fun categoryPageViewPixel(
        ref: String,
        title: String,
        categoryId: String,
        category: String
    ) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.PAGE_VIEW,
                pType = PageType.CATEGORY_PAGE,
                ref = ref,
                title = title,
                cat = category,
                catId = categoryId
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)

        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Search Result Page View Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param searchTerm The value of the search query describing the page.
     */
    fun searchResultPageViewPixel(ref: String, title: String, searchTerm: String) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.PAGE_VIEW,
                pType = PageType.SEARCH_PAGE,
                ref = ref,
                title = title,
                searchTerm = searchTerm
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)

        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Conversion Page View Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param isConversion Set to true to indicate this is a Conversion or Thank you page
     * @param basketValue The total price of the checkout basket including tax, discounts, shipping and/or discounts in the account currency.
     * @param orderId The order ID associated with the order placed
     * @param basket  List of the PixelBasketItem objects (Products purchased).
     */
    fun conversionPageView(
        ref: String,
        title: String,
        isConversion: Boolean,
        basketValue: String,
        orderId: String,
        basket: List<PixelBasketItem>
    ) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.PAGE_VIEW,
                pType = PageType.CONVERSION,
                ref = ref,
                title = title,
                isConversion = if (isConversion) 1 else 0,
                basketValue = basketValue,
                orderId = orderId,
                basket = basket
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)

        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method to send any type of Page View Pixel with Custom Parameters
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param pType Maps your site's page type classifications to the values Bloomreach expects for our page type classifications.
     * @param params Map for custom keys and its associated values
     */
    fun customPageViewPixel(
        ref: String,
        title: String,
        pType: PageType,
        params: MutableMap<String, String?>
    ) {
        if (this::brPixel.isInitialized) {
            // directly add map to the PixelQueue
            params["ref"] = ref
            params["title"] = title
            params["type"] = PixelType.PAGE_VIEW.type
            params["ptype"] = pType.pType
            pixelProcessor.processPixel(params)

        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    //==================== EVENT PIXELS =======================

    /**
     * Method for sending the Add To Cart Event Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param prodId This is the unique ID which describes a product or product collection.
     * @param prodName The name of the product being viewed.
     * @param sku Unique sku ID that represents the selected variant of this product. If your site does not have SKUs, leave this blank.
     * @param prodCollectionId (Optional) When a product is added to cart from a Product Collection page, set prod_collection_id as the id of the collection.
     *                          No need to set prod_collection_id param in the ATC event pixel when a product is added to cart from its own page, independent of any Product Collection it is part of.
     */
    fun addToCartEventPixel(
        ref: String,
        title: String,
        prodId: String,
        prodName: String,
        sku: String,
        prodCollectionId: String? = null
    ) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.EVENT,
                pType = PageType.PRODUCT_PAGE,
                group = GroupType.CART,
                eType = "click-add",
                ref = ref,
                title = title,
                prodId = prodId,
                prodName = prodName,
                prodSku = sku,
                prodCollectionId = prodCollectionId
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)

        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Search Event Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param prodId This is the unique ID which describes a product or product collection.
     * @param prodName The name of the product being viewed.
     * @param sku Unique sku ID that represents the selected variant of this product. If your site does not have SKUs, leave this blank.
     * @param searchTerm The value of the search query describing the page.
     * @param catalogs List of CatalogItem that are shown in the page.
     */
    @Deprecated("This method will be removed in future version. Instead use searchEventPixel(ref, title, searchTerm, catalogs)")
    fun searchEventPixel(
        ref: String,
        title: String,
        prodId: String,
        prodName: String,
        sku: String,
        searchTerm: String,
        catalogs: List<CatalogItem>? = null
    ) {
        if (this::brPixel.isInitialized) {

            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.EVENT,
                pType = PageType.PRODUCT_PAGE,
                group = GroupType.SUGGEST,
                eType = "submit",
                ref = ref,
                title = title,
                prodId = prodId,
                prodName = prodName,
                prodSku = sku,
                searchTerm = searchTerm,
                catalogs = catalogs
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }


    /**
     * Method for sending the Search Event Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param searchTerm The value of the search query describing the page.
     * @param catalogs List of CatalogItem that are shown in the page.
     */
    fun searchEventPixel(
        ref: String,
        title: String,
        searchTerm: String,
        catalogs: List<CatalogItem>? = null
    ) {
        if (this::brPixel.isInitialized) {

            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.EVENT,
                pType = PageType.PRODUCT_PAGE,
                group = GroupType.SUGGEST,
                eType = "submit",
                ref = ref,
                title = title,
                searchTerm = searchTerm,
                catalogs = catalogs
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Suggestion Event Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param prodId This is the unique ID which describes a product or product collection.
     * @param prodName The name of the product being viewed.
     * @param sku Unique sku ID that represents the selected variant of this product. If your site does not have SKUs, leave this blank.
     * @param typedTerm The display query (the one or more letters) that the user has actually typed.
                        This is NOT the suggested word or phrase.
     * @param searchTerm User's typed search query submitted to search box
     * @param catalogs List of CatalogItem that are shown in the page.
     */
    fun suggestionEventPixel(
        ref: String,
        title: String,
        prodId: String,
        prodName: String,
        sku: String,
        typedTerm: String,
        searchTerm: String,
        catalogs: List<CatalogItem>? = null
    ) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.EVENT,
                pType = PageType.PRODUCT_PAGE,
                group = GroupType.SUGGEST,
                eType = "click",
                ref = ref,
                title = title,
                prodId = prodId,
                prodName = prodName,
                prodSku = sku,
                searchTerm = searchTerm,
                typedTerm = typedTerm,
                catalogs = catalogs
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Quick Event Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param prodId This is the unique ID which describes a product or product collection.
     * @param prodName The name of the product being viewed.
     * @param sku Unique sku ID that represents the selected variant of this product. If your site does not have SKUs, leave this blank.
     */
    fun quickViewEventPixel(
        ref: String,
        title: String,
        prodId: String,
        prodName: String,
        sku: String
    ) {
        if (this::brPixel.isInitialized) {
            // create pixel object based ob input
            val pixelObject = PixelObject(
                type = PixelType.EVENT,
                pType = PageType.PRODUCT_PAGE,
                group = GroupType.PRODUCT,
                eType = "quickview",
                ref = ref,
                title = title,
                prodId = prodId,
                prodName = prodName,
                prodSku = sku
            )

            // send pixel for further processing
            pixelProcessor.processPixel(pixelObject)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Custom Event Pixel
     * @param ref Synthetic URL from referrer screen
     * @param title Screen name of the app view.
     * @param eType Event type
     * @param pType Maps your site's page type classifications to the values Bloomreach expects for our page type classifications.
     * @param group Specifies the event grouping
     * @param params Map for custom keys and its associated values
     */
    fun customEventPixel(
        ref: String,
        title: String,
        eType: String,
        pType: PageType,
        group: GroupType,
        params: MutableMap<String, String?>
    ) {
        if (this::brPixel.isInitialized) {
            // directly add map to the PixelQueue
            params["ref"] = ref
            params["title"] = title
            params["type"] = PixelType.EVENT.type
            params["etype"] = eType
            params["ptype"] = pType.pType
            params["group"] = group.group
            pixelProcessor.processPixel(params)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    //==================== WIDGET PIXELS =======================

    /**
     * Method for sending the Widget View Pixel
     * @param widgetDataWrId The unique ID of the response. This value has to be populated from the API response metadata.widget.rid.
     * @param widgetViewDataWq The query string used by the customer which returns a widget suggestion. This is optional for non-query widgets.
     * @param widgetViewDataWid The widget ID. This is a unique, 6 character alphanumeric value. This value has to be populated from the API response metadata.widget.id.
     * @param widgetViewDataWty The type of recommendation widget. This value has to be populated from the API response.This value has to be populated from the API response metadata.widget.type.
     */
    fun widgetView(
        widgetDataWrId: String,
        widgetViewDataWq: String,
        widgetViewDataWid: String,
        widgetViewDataWty: String
    ) {
        if (this::brPixel.isInitialized) {
            val params = mutableMapOf<String, String?>()
            params["type"] = PixelType.EVENT.type
            params["group"] = GroupType.WIDGET.group
            params["etype"] = "widget-view"
            params["wrid"] = widgetDataWrId
            params["wq"] = widgetViewDataWq
            params["wid"] = widgetViewDataWid
            params["wty"] = widgetViewDataWty
            pixelProcessor.processPixel(params)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Widget Click Pixel
     * @param widgetDataWrId The unique ID of the response.
     * @param widgetViewDataWq The query string used by the customer which returns a widget suggestion. This is optional for non-query widgets.
     * @param widgetViewDataWid The widget ID. This is a unique, 6 character alphanumeric value. This value has to be populated from the API response metadata.widget.id.
     * @param widgetViewDataWty The type of recommendation widget. This value has to be populated from the API response.This value has to be populated from the API response metadata.widget.type.
     * @param widgetDataItemId The unique ID (PID) of the clicked product. The PID is used from the API call.
     */
    fun widgetClick(
        widgetDataWrId: String,
        widgetViewDataWq: String,
        widgetViewDataWid: String,
        widgetViewDataWty: String,
        widgetDataItemId: String
    ) {
        if (this::brPixel.isInitialized) {
            val params = mutableMapOf<String, String?>()
            params["type"] = PixelType.EVENT.type
            params["group"] = GroupType.WIDGET.group
            params["etype"] = "widget-click"
            params["ptype"] = PageType.OTHER_PAGE.pType

            params["item_id"] = widgetDataItemId
            params["wrid"] = widgetDataWrId
            params["wq"] = widgetViewDataWq
            params["wid"] = widgetViewDataWid
            params["wty"] = widgetViewDataWty
            pixelProcessor.processPixel(params)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }

    /**
     * Method for sending the Widget Add to cart
     * @param widgetDataWrId The unique ID of the response. This value has to be populated from the API response metadata.widget.rid.
     * @param widgetViewDataWq The query string used by the customer which returns a widget suggestion. This is optional for non-query widgets.
     * @param widgetViewDataWid The widget ID. This is a unique, 6 character alphanumeric value. This value has to be populated from the API response metadata.widget.id.
     * @param widgetViewDataWty The type of recommendation widget. This value has to be populated from the API response.This value has to be populated from the API response metadata.widget.type.
     * @param widgetDataItemId The unique ID (PID) of the clicked product. The PID is used from the API call.
     * @param widgetAtcDataSku Unique SKU id that represents the selected variant of this product (e.g. size M, color blue of a t-shirt). If your site does not have SKUs, leave this blank.
     */
    fun widgetAddToCart(
        widgetDataWrId: String,
        widgetViewDataWid: String,
        widgetViewDataWq: String,
        widgetViewDataWty: String,
        widgetDataItemId: String,
        widgetAtcDataSku: String? = null
    ) {
        if (this::brPixel.isInitialized) {
            val params = mutableMapOf<String, String?>()
            params["type"] = PixelType.EVENT.type
            params["group"] = GroupType.CART.group
            params["etype"] = "widget-add"
            params["item_id"] = widgetDataItemId
            params["wrid"] = widgetDataWrId
            params["wid"] = widgetViewDataWid
            params["wq"] = widgetViewDataWq
            params["wty"] = widgetViewDataWty
            if(!widgetAtcDataSku.isNullOrEmpty()) {
                params["sku"] = widgetAtcDataSku
            }
            pixelProcessor.processPixel(params)
        } else {
            Log.e(TAG, "Pixel Tracker not initialised")
        }
    }
}