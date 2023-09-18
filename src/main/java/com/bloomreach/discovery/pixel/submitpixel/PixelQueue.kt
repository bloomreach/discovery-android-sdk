/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.submitpixel

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

/**
 * PixelQueue class to hold pixels query parameter map to be sent to PIXEL API
 */
internal object PixelQueue {
    val pixels: ListenableQueue<MutableMap<String, String?>> =
        ListenableQueue(LinkedList())

    /**
     * Method to add Pixel to Queue
     * @param queryMap Map of query parameter
     */
    fun add(queryMap: MutableMap<String, String?>) {
        // add to Queue
        pixels.add(queryMap)
    }

    /**
     * Method to pick Pixel from Queue
     * @return Map of query parameters
     */
    fun get(): MutableMap<String, String?>? {
        // Get the element at the front of the Queue (without removing it)
        // if the Queue is empty, element() throws NoSuchElementException while peek() returns null.
        return pixels.peek()
    }

    /**
     * Method to remove Pixel from Queue
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun remove() {
        //remove an element from the Queue (dequeue operation)
        //poll() method is similar to remove() (dequeue operation), but it returns null if the Queue is empty instead of throwing an exception.
        pixels.poll()
    }
}