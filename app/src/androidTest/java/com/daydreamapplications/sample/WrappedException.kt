package com.daydreamapplications.sample

data class WrappedException(val origin: Throwable): RuntimeException() {

    override fun toString(): String {
        return origin.toString()
    }

    override fun printStackTrace() {
        origin.printStackTrace()
    }
}