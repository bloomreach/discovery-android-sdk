/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.api.request

/**
 * RequestMap class base class for all Request class, which stores the parameters in the map
 */
sealed class RequestMap<T> {
    private val requestMap = mutableMapOf<String, Any?>()

    /**
     * Method to set query parameter as key and value.
     * If the key is already set, the value will get replaced
     *
     * @param key  The name of the query parameter
     * @param value The value that is used for the query parameter value. If the value is
     * <pre>null</pre> the key will be removed
     *
     * @return  A reference to the current Request object
     */
    fun set(key: String, value: String?): T {
        if (key.isEmpty()) {
            throw IllegalArgumentException("Key cannot be empty")
        }

        if (value.isNullOrEmpty()) {
            requestMap.remove(key)
        } else {
            requestMap[key] = value
        }
        return this as T
    }

    /**
     * Method to add multiple query parameter for same key
     *
     * @param key  The name of the query parameter
     * @param value The value that is used for the query parameter value
     *
     * @return  A reference to the current Request object
     */
    fun add(key: String, value: String?): T {
        if (key.isEmpty()) {
            throw IllegalArgumentException("Key cannot be empty")
        }

        if (!value.isNullOrEmpty()) {
            if (requestMap.containsKey(key)) {
                val mapValue = requestMap[key]
                if (mapValue is ArrayList<*>) {
                    (mapValue as ArrayList<String>).add(value)
                    requestMap[key] = mapValue
                } else if (mapValue is String) {
                    val list = ArrayList<String>()
                    list.add(mapValue as String)
                    list.add(value)
                    requestMap[key] = list
                }
            } else {
                requestMap[key] = value
            }
        } else {
            requestMap.remove(key)
        }
        return this as T
    }

    /**
     * Method to get Request Map object
     *
     * @return  A reference request map object
     */
    fun getMap(): MutableMap<String, Any?> {
        return requestMap
    }
}