/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.network

/**
 * RetryStrategy - Retry mechanism for Pixel call with network failure scenerios
 */
class RetryStrategy {

    private val DEFAULT_RETRIES = 3
    private val DEFAULT_WAIT_TIME_IN_MILLI: Long = 1000

    private var numberOfRetries = DEFAULT_RETRIES
    private var numberOfTriesLeft = 3
    private var timeToWait: Long = DEFAULT_WAIT_TIME_IN_MILLI

    /**
     * @return true if there are tries left
     */
    fun shouldRetry(): Boolean {
        return numberOfTriesLeft > 0
    }

    @Throws(Exception::class)
    fun errorOccured() {
        if (!shouldRetry()) {
            throw Exception(
                "Retry Failed: Total " + numberOfRetries
                        + " attempts made at interval " + getTimeToWait()
                        + "ms"
            )
        }
        numberOfTriesLeft--
        waitUntilNextTry()
    }

    private fun getTimeToWait(): Long {
        return timeToWait
    }

    private fun waitUntilNextTry() {
        try {
            Thread.sleep(getTimeToWait())
        } catch (ignored: InterruptedException) {

        }
    }
}