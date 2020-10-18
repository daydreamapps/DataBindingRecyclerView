package com.daydreamapplications.bindingrecycler

import androidx.lifecycle.*

/**
 * Utility class to safely observe and relay the state of a LifecycleOwner
 *
 * @see LifecycleObserver
 * @see LifecycleOwner
 */
open class LifecycleRelay : LifecycleObserver, LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }
}
