/*
 * Copyright 2022 Bloomreach
 */

package com.bloomreach.discovery.pixel.submitpixel

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import java.util.function.Consumer

/**
 * ListenableQueue Decorator pattern class to provide callbacks when item is added or removed from the Queue
 */
class ListenableQueue<E>(
    var delegate: Queue<E>
) : AbstractQueue<E>() {
    private val listeners: MutableList<Listener<E>> = ArrayList()

    fun registerListener(listener: Listener<E>): ListenableQueue<E> {
        listeners.add(listener)
        return this
    }

    override fun iterator(): MutableIterator<E> {
        return delegate.iterator()
    }

    override fun offer(e: E): Boolean {
        // here, add the element and if Queue was empty then notify listeners
        if (delegate.size == 0) {
            return if (delegate.offer(e)) {
                listeners.forEach(Consumer { listener: Listener<E> ->
                    listener.onElementAdded(e)
                })
                true
            } else {
                false
            }
        }
        return delegate.offer(e)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun poll(): E? {
        // here, remove the element and if Queue was not empty then notify listeners
        val element = delegate.poll()
        if (delegate.size > 0) {
            listeners.forEach(Consumer { listener: Listener<E> ->
                listener.onElementRemoved(element)
            })
        }
        return element
    }

    override fun peek(): E? {
        return delegate.peek()
    }

    override val size: Int
        get() = delegate.size

    interface Listener<E> {
        fun onElementAdded(element: E)
        fun onElementRemoved(element: E)
    }
}