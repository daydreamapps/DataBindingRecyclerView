package com.daydreamapplications.bindingrecycler

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LifecycleRelayLifecycleTestTest {

    private val lifecycleRelay = LifecycleRelay()


    @Test
    fun constructor_lifecycleState_INITIALIZED() {
        assertThat(lifecycleRelay.lifecycle.currentState).isEqualTo(Lifecycle.State.INITIALIZED)
    }

    @Test
    fun onCreate_lifecycleState_CREATED() {
        lifecycleRelay.onCreate()

        assertThat(lifecycleRelay.lifecycle.currentState).isEqualTo(Lifecycle.State.CREATED)
    }

    @Test
    fun onStart_lifecycleState_STARTED() {
        lifecycleRelay.onStart()

        assertThat(lifecycleRelay.lifecycle.currentState).isEqualTo(Lifecycle.State.STARTED)
    }

    @Test
    fun onResume_lifecycleState_RESUMED() {
        lifecycleRelay.onResume()

        assertThat(lifecycleRelay.lifecycle.currentState).isEqualTo(Lifecycle.State.RESUMED)
    }

    @Test
    fun onPause_lifecycleState_STARTED() {
        lifecycleRelay.onPause()

        assertThat(lifecycleRelay.lifecycle.currentState).isEqualTo(Lifecycle.State.STARTED)
    }

    @Test
    fun onStop_lifecycleState_CREATED() {
        lifecycleRelay.onStop()

        assertThat(lifecycleRelay.lifecycle.currentState).isEqualTo(Lifecycle.State.CREATED)
    }

    @Test
    fun onDestroy_lifecycleState_DESTROYED() {
        lifecycleRelay.onDestroy()

        assertThat(lifecycleRelay.lifecycle.currentState).isEqualTo(Lifecycle.State.DESTROYED)
    }
}
