/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.validator

import android.util.Log
import com.bloomreach.discovery.pixel.PixelTracker
import com.bloomreach.discovery.pixel.model.validator.PixelValidatorBody
import com.bloomreach.discovery.pixel.model.validator.response.PixelValidatorResponse
import com.bloomreach.discovery.pixel.network.RestClient
import com.bloomreach.discovery.pixel.processpixel.FormatterUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * PixelValidator class to format Pixel Validator request and perform the API call and format the response
 */
internal class PixelValidator {
    private val restClient: RestClient = RestClient()
    private val protocol = "https"
    private val path = "pix.gif"

    /**
     * Method to format the Pixel validator request in required format and perform validator API call
     *
     * @param queryMap Map of pixel values in required format
     */
    fun validatePixel(queryMap: MutableMap<String, String?>) {

        // operations to form post body
        val uriBuilder = FormatterUtils.mapToUriBuilder(queryMap)
        val query = uriBuilder.build().toString()
        uriBuilder.scheme(protocol)
        uriBuilder.authority(PixelTracker.brPixel.pixelUrlByRegion)
        uriBuilder.appendPath(path)

        val postBody = PixelValidatorBody(
            source = uriBuilder.build().toString(),
            protocol = protocol,
            host = PixelTracker.brPixel.pixelUrlByRegion,
            port = "",
            query = query,
            params = queryMap,
            file = path,
            hash = "",
            path = "/$path",
            relative = "/$path$query",
            segments = listOf<String>(path)
        )

//        for Rest Call
        CoroutineScope(Dispatchers.IO).launch {
            // Validate Pixel REST call
            val response = restClient.validatePixel(postBody)
            printPixelValidatorResponse(response)
        }
    }

    /**
     * Method to format the Pixel validator response object in required format and print it to logs.
     *
     * @param response PixelValidatorResponse received from Pixel validator call
     */
    private fun printPixelValidatorResponse(response: PixelValidatorResponse?) {
        if (response == null) {
            return
        }

        if (!response.errors.isNullOrEmpty()) {
            Log.v(
                "Pixel Validator",
                "\n==========PIXEL VALIDATOR ERROR========= \n\n${formResponseString(response)} \n\n"
            )
        } else if (!response.warns.isNullOrEmpty()) {
            Log.v(
                "Pixel Validator",
                "\n==========PIXEL VALIDATOR WARNING========= \n\n${formResponseString(response)} \n\n"
            )
        } else {
            Log.v(
                "Pixel Validator",
                "\n==========PIXEL VALIDATOR SUCCESS========= \n\n${formResponseString(response)} \n\n"
            )
        }
    }

    /**
     * Method to format the Pixel validator response object in required format
     *
     * @param response PixelValidatorResponse received from Pixel validator call
     */
    private fun formResponseString(response: PixelValidatorResponse): String {
        return buildString {
            this.append("Pixel Name: ${response.displayName}\n\n")

            if (!response.errors.isNullOrEmpty()) {
                this.append("Error in Parameters ==>")
                for (error in response.errors) {
                    this.append("${error.name}: ${error.value}\n")
                    this.append("Description: ${error.description}\n\n")
                }
            }

            if (!response.warns.isNullOrEmpty()) {
                this.append("Warning in Parameters ==>\n")
                for (warn in response.warns) {
                    this.append("${warn.name}: ${warn.value}\n")
                    this.append("Description: ${warn.description}\n\n")
                }
            }

            if (!response.success.isNullOrEmpty()) {
                this.append("Success Parameters ==>\n")
                for (success in response.success) {
                    this.append("${success.name}: ${success.value}\n")
                }
            }
        }
    }


}