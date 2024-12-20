package com.adwhalemediationadandroidrn.kotlin

import android.util.Log
import com.adwhalemediationadandroidrn.java.RNAdWhaleMediationAdSettingModule
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import net.adwhale.sdk.mediation.ads.AdWhaleMediationAds

class RNAdWhaleMediationAdSettingModuleKt(context : ReactApplicationContext?) : ReactContextBaseJavaModule(context!!) {

    companion object {
        private val REACT_CLASS_NAME = RNAdWhaleMediationAdSettingModuleKt::class.java.simpleName
    }

    override fun getName() : String {
        return REACT_CLASS_NAME
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    fun initialize(promise: Promise) : Unit {
        Log.i(REACT_CLASS_NAME, "currentActivity: $currentActivity")
        AdWhaleMediationAds.init(currentActivity) {
            statusCode, message -> Log.i(REACT_CLASS_NAME, ".onInitComplete($statusCode, ${message});")
            promise.resolve(statusCode)
        }
    }

    override fun getConstants() : Map<String, String> {
        return mapOf<String, String>("" to "");
    }

}

