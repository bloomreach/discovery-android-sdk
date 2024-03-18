/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api

import com.bloomreach.discovery.api.model.Env
import com.bloomreach.discovery.pixel.model.VisitorType

/**
 * Class containing initialising parameters for the API SDK.
 *
 * @property accountId Account Id provided by Bloomreach
 * @property uuid Android Advertising ID
 * @property visitorType ENUM type for New User or returning user
 * @property domainKey The Bloomreach-provided ID of the domain receiving the request.
 * @property authKey This parameter is only required if you track users via a universal customer ID.
 * @property userId This parameter is only required if you track users via a universal customer ID.
 * @property environment ENUM for api to be pointed to which version., STAGE or PROD
 */
data class BrApiRequest(
    val accountId: String,
    val uuid: String,
    val visitorType: VisitorType,
    val domainKey: String,
    var authKey: String? = null,
    var userId: String? = null,
    var environment: Env = Env.STAGE,

    //rts
    var cdpSegment: String? = null
)
