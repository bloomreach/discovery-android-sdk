/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.submitpixel

import com.bloomreach.discovery.pixel.processpixel.FormatterUtils
import com.bloomreach.discovery.pixel.network.RestClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * PixelJob class to trigger submit api call continuously if Queue is not empty
 */
internal class PixelJob {
    private val restClient: RestClient = RestClient()
    var isInProgress = false

    /**
     * Method to keep continuously push the Pixels from Queue to API call and removing from Queue
     */
    fun pushPixels() {
        if (PixelQueue.get() != null) {
            isInProgress = true
            val uriBuilder = FormatterUtils.mapToUriBuilder(PixelQueue.get()!!)
            CoroutineScope(Dispatchers.IO).launch {
                val response = restClient.submitPixel(uriBuilder)
                //Log.i("PixelJob", response.toString())
               // PixelQueue.remove()
                if (PixelQueue.get() != null) {
                    // if Pixels are lying in Queue, pick the next pixel.
                    pushPixels()
                } else {
                    isInProgress = false
                }
            }
        } else {
            isInProgress = true
        }
    }
}