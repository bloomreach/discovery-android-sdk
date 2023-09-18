/*
 * Copyright 2022 Bloomreach
 */
package com.bloomreach.discovery.pixel.model

/**
 * Class containing initialising parameters for the Pixel SDK.
 *
 * @property accountId Account Id provided by Bloomreach
 * @property uuid Android Advertising ID
 * @property visitorType ENUM type for New User or returning user
 * @property baseUrl Base Url for the merchant provided bt Bloomreach
 * @property domainKey The Bloomreach-provided ID of the domain receiving the request.(Optional)
 * @property userId This parameter is only required if you track users via a universal customer ID.
 * @property testData Do not declare test_data in the pixel for your live site.
 * @property currency Currency for the app
 * @property pixelUrlByRegion Url for Pixel server based on region. Default to NA region
 * @property customerTier Tier that the user belongs to. eg: Premium, Gold
 * @property customerCountry Country that the user belongs to or is accessing the site from.
 * @property customerGeo Geography or Region that the user belongs to.
 * @property customerProfile Profile of the user.
 * @property viewId View Id
 * @property debugMode Debug mode of main application to see Pixel Validator logs. Pass value as BuildConfig.DEBUG
 */

data class BrPixel(
    val accountId: String,
    val uuid: String,
    val visitorType: VisitorType,
    val baseUrl: String,
    var domainKey: String? = null,
    var userId: String? = null,
    var testData: Boolean = false,
    var currency: String? = null,
    var pixelUrlByRegion: String = PixelRegion.NA.url,
    //segments
    var customerTier: String? = null,
    var customerCountry: String? = null,
    var customerGeo: String? = null,
    var customerProfile: String? = null,
    var viewId: String? = null,
    var debugMode: Boolean = false
) {
    init {
        require(accountId.isNotEmpty()) { "Bloomreach Account Id is required" }
        require(uuid.isNotEmpty()) { "UUID is required" }
       // require(visitor.isNotEmpty()) { "visitor is required" }
        require(baseUrl.isNotEmpty()) { "baseUrl is required" }
    }
}
