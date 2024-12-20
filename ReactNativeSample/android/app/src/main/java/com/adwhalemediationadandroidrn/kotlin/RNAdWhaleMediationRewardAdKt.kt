package com.adwhalemediationadandroidrn.kotlin

import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.UiThreadUtil
import com.facebook.react.bridge.WritableMap
import net.adwhale.sdk.mediation.ads.AdWhaleMediationFullScreenContentCallback
import net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardAd
import net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardItem
import net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardedAdLoadCallback
import net.adwhale.sdk.mediation.ads.AdWhaleMediationUserEarnedRewardListener
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

class RNAdWhaleMediationRewardAdKt(private val context: ReactApplicationContext) : ReactContextBaseJavaModule(context), AdWhaleMediationRewardedAdLoadCallback, AdWhaleMediationFullScreenContentCallback, AdWhaleMediationUserEarnedRewardListener {

    companion object {
        private val REACT_CLASS_NAME = RNAdWhaleMediationRewardAdKt::class.java.simpleName
    }

    private lateinit var adWhaleMediationRewardAd: AdWhaleMediationRewardAd

    override fun getName(): String {
        return REACT_CLASS_NAME
    }

    @ReactMethod
    fun initialize(placementUid: String) {
        Log.e(REACT_CLASS_NAME, "initialize() placementUid: $placementUid")
        adWhaleMediationRewardAd = AdWhaleMediationRewardAd(placementUid)
        adWhaleMediationRewardAd.setAdWhaleMediationFullScreenContentCallback(this)
    }

    @ReactMethod
    fun loadAd() {
        Log.e(REACT_CLASS_NAME, "loadAd()")

        UiThreadUtil.runOnUiThread {
            adWhaleMediationRewardAd.loadAd(this)
        }
    }

    @ReactMethod
    fun showAd() {
        Log.e(REACT_CLASS_NAME, "showAd()")

        UiThreadUtil.runOnUiThread {
            adWhaleMediationRewardAd.showAd(this)
        }
    }


    /// AdWhaleMediationFullScreenContentCallback
    override fun onAdClicked() {
        Log.i(REACT_CLASS_NAME, "onAdClicked()")
        sendEvent("onRewardAdClicked", null)
    }

    override fun onAdDismissed() {
        Log.i(REACT_CLASS_NAME, "onAdDismissed()")
        sendEvent("onRewardAdDismissed", null)
    }

    override fun onFailedToShow(statusCode: Int, message: String?) {
        Log.i(REACT_CLASS_NAME, "onFailedToShow($statusCode, ${message})")
        val params = Arguments.createMap()
        params.putInt("statusCode", statusCode)
        params.putString("message", message)
        sendEvent("onRewardAdFailedToShow", params)
    }

    override fun onAdShowed() {
        Log.i(REACT_CLASS_NAME, "onAdShowed()")
        sendEvent("onRewardAdShowed", null)
    }

    /// AdWhaleMediationRewardedAdLoadCallback
    override fun onAdLoaded(adWhaleMediationrewardAd: AdWhaleMediationRewardAd?, message: String?) {
        Log.i(REACT_CLASS_NAME, "onAdLoaded($message)")
        sendEvent("onRewardAdLoaded", null)
    }

    override fun onAdFailedToLoad(statusCode: Int, message: String?) {
        Log.i(REACT_CLASS_NAME, "onAdFailedToLoad($statusCode, ${message})")
        val params = Arguments.createMap()
        params.putInt("statusCode", statusCode)
        params.putString("message", message)
        sendEvent("onRewardAdFailedToLoad", params)
    }

    /// AdWhaleMediationUserEarnedRewardListener
    override fun onUserRewarded(adWhaleMediationRewardItem: AdWhaleMediationRewardItem?) {
        Log.i(REACT_CLASS_NAME, "onUserRewarded(${adWhaleMediationRewardItem.toString()})")
        val params = Arguments.createMap()
        params.putString("type", adWhaleMediationRewardItem?.rewardType)
        params.putInt("amount", adWhaleMediationRewardItem?.rewardAmount ?: 0)
        sendEvent("onUserRewarded", params)
    }

    private fun sendEvent(eventName: String, params: WritableMap?) {
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