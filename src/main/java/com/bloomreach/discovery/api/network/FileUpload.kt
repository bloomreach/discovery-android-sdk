/*
 * Copyright 2023 Bloomreach
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
import com.bloomreach.discovery.api.model.visualsearch.ImageUploadResponse
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 *  Class to perform File Upload API call
 */
internal class FileUpload(url: URL) {
    companion object {
        private val LINE_FEED = "\r\n"
        private val maxBufferSize = 1024 * 1024
        private val charset = "UTF-8"
    }
        // creates a unique boundary based on time stamp
        private val boundary: String = "===" + System.currentTimeMillis() + "==="
        private val httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        private val outputStream: OutputStream
        private val writer: PrintWriter

        init {
            httpConnection.setRequestProperty("Accept-Charset", "UTF-8")
            httpConnection.setRequestProperty("Connection", "Keep-Alive")
            httpConnection.setRequestProperty("Cache-Control", "no-cache")
            httpConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary)
            httpConnection.setRequestProperty("auth_key", BrApi.brApiRequest.authKey)
            httpConnection.setRequestProperty(
                "User-Agent",
                "Bloomreach/${BuildConfig.SDK_VERSION} " + System.getProperty("http.agent")
            )
            httpConnection.setChunkedStreamingMode(maxBufferSize)
            httpConnection.doInput = true
            httpConnection.doOutput = true    // indicates POST method
            httpConnection.useCaches = false
            outputStream = httpConnection.outputStream
            writer = PrintWriter(OutputStreamWriter(outputStream, charset), true)
        }

    /**
     * Method to add file part to the API call
     * @param inputStream inputStream of the image
     * @param fileName file name of the image
     */
    @Throws(IOException::class)
    fun addFilePart(inputStream: InputStream, fileName: String) {
        writer.append("--").append(boundary).append(LINE_FEED)
        writer.append("Content-Disposition: form-data; name=\"").append("image")
            .append("\"; filename=\"").append(fileName).append("\"").append(LINE_FEED)
        writer.append("Content-Type: ").append("image/jpeg").append(LINE_FEED)
        writer.append(LINE_FEED)
        writer.flush()

        inputStream.copyTo(outputStream, maxBufferSize)

        outputStream.flush()
        inputStream.close()
        writer.append(LINE_FEED)
        writer.flush()
    }

    /**
     * Method to perform File Upload API
     * @return Any response object CoreResponse if API call success else return BrApiError object
     */
    @Throws(IOException::class)
    fun upload() : Any {
        writer.append(LINE_FEED).flush()
        writer.append("--").append(boundary).append("--")
            .append(LINE_FEED)
        writer.close()

        try {
            // checks server's status code first
            val responseCode = httpConnection.responseCode
            Log.i("Visual Search upload API CALL:", "responseCode: $responseCode")
            if (responseCode in 200..299)  {
                val reader = BufferedReader(InputStreamReader(httpConnection
                    .inputStream))
                val response = reader.use(BufferedReader::readText)
                httpConnection.disconnect()
                println("res: $response")
                val responseMapper = ObjectMapper()
                return responseMapper.readValue(response, ImageUploadResponse::class.java)
            } else {
                if (httpConnection.errorStream != null) {
                    val result = httpConnection.errorStream.bufferedReader().use(BufferedReader::readText)
                    httpConnection.errorStream.close()
                    //covert error result to BrApiError object
                    print("error: $result")

                    val responseMapper = ObjectMapper()
                    val rpError = responseMapper.readValue(result, RpError::class.java)
                    return BrApiError(rpError.detail ?: "Something went wrong", responseCode)

                }
                return BrApiError("Something went wrong", responseCode)
            }

        } catch (e: Exception) {
            Log.e("Visual Search upload API CALL::", "Error: ${e.localizedMessage}")
            return BrApiError("Something went wrong", 0)
        }
    }
}