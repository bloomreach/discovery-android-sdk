/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.network

import android.util.Log
import com.bloomreach.discovery.BuildConfig
import com.bloomreach.discovery.api.BrApi
import com.bloomreach.discovery.api.model.BrApiError
import com.bloomreach.discovery.api.model.core.CoreResponse
import com.bloomreach.discovery.api.model.rp.RecsAndPathwaysResponse
import com.bloomreach.discovery.api.model.rp.RpError
import com.bloomreach.discovery.api.model.suggest.SuggestResponse
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 *  Class to perform API call for API module
 */
internal class RestClientApi {

    /**
     * Method to HTTP call for all API
     * @param url URL object containing request parameters
     * @param type The type to identify of API call  such as core, suggest.
     * @return Any response object CoreResponse if API call success else return BrApiError object
     */
    fun doApiCall(url: URL, type: ApiType): Any? {
        val inputStream: InputStream
        try {
            Log.i("$type API CALL:", url.toString())

            // Create HttpURLConnection
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            // API request set to GET
            conn.requestMethod = "GET";
            // set none cache
            conn.setRequestProperty("Cache-Control", "no-cache")
            conn.setRequestProperty(
                "User-Agent",
                "Bloomreach/${BuildConfig.SDK_VERSION} " + System.getProperty("http.agent")
            )

            if (type == ApiType.PATHWAYS) {
                //v2 API requires passing the auth-key as a request
                conn.setRequestProperty("auth_key", BrApi.brApiRequest.authKey)
            }

            conn.defaultUseCaches = false;
            conn.useCaches = false;
            // Launch GET request
            conn.connect()

            val responseCode = conn.responseCode
            Log.i("$type API CALL:", "responseCode: $responseCode")
            if (responseCode in 200..299) {  // success
                // Receive response as inputStream
                inputStream = conn.inputStream

                if (inputStream != null) {
                    // Convert input stream to string
                    var result = inputStream.bufferedReader().use(BufferedReader::readText)
                    inputStream.close()
                    println("result: $result")
                    val responseMapper = ObjectMapper()
                    return when (type) {
                        ApiType.CORE -> responseMapper.readValue(result, CoreResponse::class.java)
                        ApiType.SUGGEST -> responseMapper.readValue(
                            result,
                            SuggestResponse::class.java
                        )
                        else -> responseMapper.readValue(result, RecsAndPathwaysResponse::class.java)
                    }
                } else {
                    inputStream.close()
                    return BrApiError("Something went wrong", responseCode)
                }
            } else {
                if (conn.errorStream != null) {
                    val result = conn.errorStream.bufferedReader().use(BufferedReader::readText)
                    conn.errorStream.close()
                    //covert error result to BrApiError object
                    print("error: $result")
                    return if (type == ApiType.PATHWAYS) {
                        val responseMapper = ObjectMapper()
                        val rpError = responseMapper.readValue(result, RpError::class.java)
                        BrApiError(rpError.detail ?: "Something went wrong", responseCode)
                    } else {
                            val responseMapper = ObjectMapper()
                            responseMapper.readValue(result, BrApiError::class.java)
                    }
                }
                return BrApiError("Something went wrong", responseCode)
            }
        } catch (err: Exception) {
            Log.e("$type API CALL:", "Error: ${err.localizedMessage}")
            return BrApiError("Something went wrong", 0)
        }
    }


    fun doApiCall(url: URL, type: ApiType, filePath: String): Any? {
        val inputStream: InputStream
        val outputStream: DataOutputStream
        try {
            val file = File(filePath)
            val fileInputStream = FileInputStream(file)

            Log.i("$type API CALL:", url.toString())

            // Create HttpURLConnection
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            // API request set to POST
            conn.requestMethod = "POST"
            conn.doInput = true;
            conn.doOutput = true;
            // set none cache
            conn.setRequestProperty("Cache-Control", "no-cache")
            conn.setRequestProperty(
                "User-Agent",
                "Bloomreach/${BuildConfig.SDK_VERSION} " + System.getProperty("http.agent")
            )

            if (type == ApiType.PATHWAYS) {
                //v2 API requires passing the auth-key as a request
                conn.setRequestProperty("auth_key", BrApi.brApiRequest.authKey)
            }

            conn.defaultUseCaches = false;
            conn.useCaches = false;
            // Launch GET request
            conn.connect()

            val responseCode = conn.responseCode
            Log.i("$type API CALL:", "responseCode: $responseCode")
            if (responseCode in 200..299) {  // success
                // Receive response as inputStream
                inputStream = conn.inputStream

                if (inputStream != null) {
                    // Convert input stream to string
                    var result = inputStream.bufferedReader().use(BufferedReader::readText)
                    inputStream.close()
                    // println("result: $result")
                    val responseMapper = ObjectMapper()
                    return when (type) {
                        ApiType.CORE -> responseMapper.readValue(result, CoreResponse::class.java)
                        ApiType.SUGGEST -> responseMapper.readValue(
                            result,
                            SuggestResponse::class.java
                        )
                        else -> responseMapper.readValue(result, RecsAndPathwaysResponse::class.java)
                    }
                } else {
                    inputStream.close()
                    return BrApiError("Something went wrong", responseCode)
                }
            } else {
                if (conn.errorStream != null) {
                    val result = conn.errorStream.bufferedReader().use(BufferedReader::readText)
                    conn.errorStream.close()
                    //covert error result to BrApiError object
                    print("error: $result")
                    return if (type == ApiType.PATHWAYS) {
                        val responseMapper = ObjectMapper()
                        val rpError = responseMapper.readValue(result, RpError::class.java)
                        BrApiError(rpError.detail ?: "Something went wrong", responseCode)
                    } else {
                        val responseMapper = ObjectMapper()
                        responseMapper.readValue(result, BrApiError::class.java)
                    }
                }
                return BrApiError("Something went wrong", responseCode)
            }
        } catch (err: Exception) {
            Log.e("$type API CALL:", "Error: ${err.localizedMessage}")
            return BrApiError("Something went wrong", 0)
        }
    }
}