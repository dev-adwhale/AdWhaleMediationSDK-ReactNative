package com.adwhalemediationadandroidrn.kotlin

import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.UiThreadUtil
import com.facebook.react.bridge.WritableMap
import net.adwhale.sdk.mediation.ads.AdWhaleMediationInterstitialAd
import net.adwhale.sdk.mediation.ads.AdWhaleMediationInterstitialAdListener

class RNAdWhaleMediationInterstitialAdKt(private val context: ReactApplicationContext) : ReactContextBaseJavaModule(context), AdWhaleMediationInterstitialAdListener {

    companion object {
        private  val REACT_CLASS_NAME = RNAdWhaleMediationInterstitialAdKt::class.java.simpleName
    }

    private lateinit var adWhaleMediationInterstitialAd: AdWhaleMediationInterstitialAd

    override fun getName(): String {
        return REACT_CLASS_NAME
    }

    @ReactMethod
    fun initialize(placementUid: String, promise: Promise) {
        Log.e(REACT_CLASS_NAME, "initialize() placementUid: $placementUid")
        adWhaleMediationInterstitialAd = AdWhaleMediationInterstitialAd(placementUid)
        adWhaleMediationInterstitialAd.setAdWhaleMediationInterstitialAdListener(this)
    }

    @ReactMethod
    fun loadAd() {
        Log.e(REACT_CLASS_NAME, "loadAd()")
        UiThreadUtil.runOnUiThread{
            adWhaleMediationInterstitialAd.loadAd()
        }
    }

    @ReactMethod
    fun showAd() {
        Log.e(REACT_CLASS_NAME, "showAd()")
        UiThreadUtil.runOnUiThread{
            adWhaleMediationInterstitialAd.showAd()
        }
    }

    override fun onAdLoaded() {
        Log.i(REACT_CLASS_NAME, "onAdLoaded()")
        sendEvent("onInterstitialAdLoaded", null)
    }

    override fun onAdLoadFailed(statusCode: Int, message: String) {
        Log.i(REACT_CLASS_NAME, "onAdLoadFailed($statusCode, ${message})")
        val params = Arguments.createMap()
        params.putInt("statusCode", statusCode)
        params.putString("message", message)
        sendEvent("onInterstitialAdLoadFailed", params)
    }

    override fun onAdShowed() {
        Log.i(REACT_CLASS_NAME, "onAdShowed()")
        sendEvent("onInterstitialAdShowed", null)
    }

    override fun onAdShowFailed(statusCode: Int, message: String?) {
        Log.i(REACT_CLASS_NAME, "onAdShowFailed($statusCode, ${message})")
        val params = Arguments.createMap()
        params.putInt("statusCode", statusCode)
        params.putString("message", message)
        sendEvent("onInterstitialAdShowFailed", params)
    }

    override fun onAdClosed() {
        Log.i(REACT_CLASS_NAME, "onAdClosed()")
        sendEvent("onInterstitialAdClosed", null)
    }

    fun sendEvent(eventName: String, params: WritableMap?) {
        reactApplicationContext
            .getJSModule(RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }

    @ReactMethod
    fun addListener(eventName: String) {
        //TODO NOTHING
    }

    @ReactMethod
    fun removeListeners(count: Int) {
        //TODO NOTHING
    }
}