package com.daydreamapplications.bindingrecycler

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Test

class LifecycleRelayTest {

    private val lifecycleRelay = LifecycleRelay()

    @Test
    fun `adding relay as observer does not crash`() {
        val lifecycleOwner: LifecycleOwner = mockk()
        every { lifecycleOwner.lifecycle.addObserver(lifecycleRelay) } just Runs

        lifecycleOwner.lifecycle.addObserver(lifecycleRelay)
    }


    @Test
    fun `adding observer to relay does not crash`() {
        val lifecycleObserver: LifecycleObserver = mockk()

        lifecycleRelay.lifecycle.addObserver(lifecycleObserver)
    }


    @Test
    fun `constructor - lifecycleState = INITIALIZED`() {
        assertThat(lifecycleRelay.lifecycle.currentState).isEqualTo(Lifecycle.State.INITIALIZED)
    }
}
