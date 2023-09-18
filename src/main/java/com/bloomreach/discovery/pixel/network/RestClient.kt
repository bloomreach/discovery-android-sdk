/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.network

import android.net.Uri
import android.util.Log
import com.bloomreach.discovery.BuildConfig
import com.bloomreach.discovery.pixel.PixelTracker
import com.bloomreach.discovery.pixel.model.validator.PixelValidatorBody
import com.bloomreach.discovery.pixel.model.validator.response.PixelValidatorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.BufferedReader
import java.io.InputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * RestClient class to perform API call in the SDK
 */
internal class RestClient {

    private val pixelValidatorUrl = "https://tools.bloomreach.com/pixel-validator/validatePixel"

    /**
     * Method to call Pixel Validator API
     * @param postBody required post body to be sent to Pixel Validator API
     *
     * @return PixelValidatorResponse response object
     */
    fun validatePixel(postBody: PixelValidatorBody): PixelValidatorResponse? {
        val inputStream: InputStream
        var result: String? = null

        try {
            // Create URL
            val url = URL(pixelValidatorUrl)

//            Log.i("", pixelValidatorUrl)
            // Create HttpURLConnection
            val conn: HttpsURLConnection = url.openConnection() as HttpsURLConnection
            conn.doOutput = true
            // set request method POST
            conn.requestMethod = "POST"

            // set required headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // Jackson Convertor ObjectMapper class for converting Model to required String to be sent as POST body
            val mapper = ObjectMapper()

            // OutputStream to send post request body
            val os: OutputStream = conn.outputStream
            val osw = OutputStreamWriter(os, "UTF-8")
            osw.write(mapper.writeValueAsString(postBody))

            osw.flush()
            osw.close()
            os.close() //close the OutputStream

            // Launch POST request
            conn.connect()
            if (conn.responseCode in 200..299) {
                // API Success
                // Receive response as inputStream
                inputStream = conn.inputStream

                if (inputStream != null) {
                    // Convert input stream to string
                    result = inputStream.bufferedReader().use(BufferedReader::readText)

                    //Jackson Convertor ObjectMapper convert respnse json to PixelValidatorResponse Object
                    inputStream.close()
                    val responseMapper = ObjectMapper()
                    return responseMapper.readValue(result, PixelValidatorResponse::class.java)
                } else {
                    inputStream.close()
                    Log.e("PixelValidator", "PixelValidator failure")
                }
            } else {
                // API failure
                Log.e("PixelValidator", "PixelValidator failure with response code ${conn.responseCode}")
            }
        } catch (err: Exception) {
            Log.e("PixelValidator", "Error when executing get request:  ${err.localizedMessage}")
        }
        return null
    }

    /**
     * Method to call Submit Pixel API for all types of Pixel
     * @param uriBuilder Uri.Builder with query parametes to be sent
     *
     * @return String to know success or error
     */
    fun submitPixel(uriBuilder: Uri.Builder): String? {
        // Pass each Pixel through through retry Retry Strategy
        val retryStrategy = RetryStrategy()
        //check if retry is needed
        while (retryStrategy.shouldRetry()) {
            val inputStream: InputStream
            var result: String? = null
            try {
                // append base endpoint for Pixel call
                uriBuilder.scheme("https")
                uriBuilder.authority(PixelTracker.brPixel.pixelUrlByRegion)
                uriBuilder.appendPath("pix.gif")
                // Create URL
                val url = URL(uriBuilder.build().toString())

                Log.i("Submit Pixel ", uriBuilder.toString())

                // Create HttpURLConnection
                val conn: HttpsURLConnection = url.openConnection() as HttpsURLConnection
                //  conn.doOutput = true
                // API request set to GET
                conn.requestMethod = "GET";
                // set none cache
                conn.setRequestProperty("Cache-Control", "no-cache")

                // set user agent property with required SDK version

                conn.setRequestProperty(
                    "User-Agent",
                    "Bloomreach/${BuildConfig.SDK_VERSION} " + System.getProperty("http.agent")
                )

                conn.defaultUseCaches = false;
                conn.useCaches = false;
                // Launch GET request
                conn.connect()

                val responseCode = conn.responseCode
                Log.i("SubmitPixel", "responseCode: $responseCode")
                if (responseCode in 200..299) {  // success
                    // Receive response as inputStream
                    inputStream = conn.inputStream

                    if (inputStream != null) {
                        // Convert input stream to string
                        result = inputStream.bufferedReader().use(BufferedReader::readText)

                        inputStream.close()
                        return result
                    } else {
                        result = "error: inputStream is null"
                        inputStream.close()
                        return result
                    }
                } else if (responseCode in 400..499) {
                    // skip the pixel and don't retry
                    return null
                } else {
                    // if error inform retryStrategy class to check if to continue with retry
                    retryStrategy.errorOccured()
                }

            } catch (err: Exception) {
                Log.e("SubmitPixel", "Error: ${err.localizedMessage}")
                retryStrategy.errorOccured()
            }
        }
        return null
    }
}