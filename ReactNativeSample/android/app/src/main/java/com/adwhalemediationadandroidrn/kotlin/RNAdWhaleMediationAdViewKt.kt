package com.adwhalemediationadandroidrn.kotlin

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RelativeLayout
import com.adwhalemediationadandroidrn.java.RNWrapperView
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.events.RCTEventEmitter
import net.adwhale.sdk.mediation.ads.ADWHALE_AD_SIZE
import net.adwhale.sdk.mediation.ads.AdWhaleMediationAdView
import net.adwhale.sdk.mediation.ads.AdWhaleMediationAdViewListener
import net.adwhale.sdk.mediation.ads.AdWhaleMediationAds

class RNAdWhaleMediationAdViewKt(private val rnAdWhaleMediationAdSettingModuleKt: RNAdWhaleMediationAdSettingModuleKt) : ViewGroupManager<RNWrapperViewKt>(), AdWhaleMediationAdViewListener {

    companion object {
        private val REACT_CLASS_NAME = RNAdWhaleMediationAdViewKt::class.java.simpleName
    }

    private lateinit var rootView : RNWrapperViewKt

    private lateinit var adWhaleMediationAdView : AdWhaleMediationAdView

    override fun getName() : String {
        return REACT_CLASS_NAME
    }

    override fun createViewInstance(themedReactContext : ThemedReactContext) : RNWrapperViewKt {
        rootView = RNWrapperViewKt(themedReactContext)
        Log.e(REACT_CLASS_NAME, "rootView created.")
        Log.i(REACT_CLASS_NAME, "currentActivity: ${themedReactContext.currentActivity}")


        adWhaleMediationAdView = AdWhaleMediationAdView(themedReactContext.currentActivity)
        adWhaleMediationAdView.adWhaleMediationAdViewListener = this

        val params : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.CENTER_HORIZONTAL)
        rootView.addView(adWhaleMediationAdView, params)
        Log.e(REACT_CLASS_NAME, "adwhaleMediationAdView is attached to rootView.")

        return rootView
    }

    @ReactProp(name = "placementUid")
    fun setPlacementUid(view : RNWrapperViewKt, placementUid : String) {
        adWhaleMediationAdView.placementUid = placementUid
    }

    @ReactProp(name = "adSize")
    fun setAdSize(view : RNWrapperViewKt, adSize : String) {
        adWhaleMediationAdView.adwhaleAdSize = ADWHALE_AD_SIZE.valueOf(adSize)
    }

    @ReactProp(name = "loadAd")
    fun loadAd(view : RNWrapperViewKt, isReload : Boolean) {
        Log.e(REACT_CLASS_NAME, "loadAd()")
        if (isReload) {
            adWhaleMediationAdView.loadAd()
        }
    }

    override fun onAdLoaded() {
        Log.e(REACT_CLASS_NAME, "onAdLoaded()")
        val context : Context = rootView.context
        if(context is ReactContext){
            context.getJSModule(RCTEventEmitter::class.java).receiveEvent(
                rootView.id, "onAdLoaded", null)
        }
    }

    override fun onAdLoadFailed(statusCode : Int, message : String) {
        Log.e(REACT_CLASS_NAME, "onAdLoadFailed($statusCode, ${message})")
        val context : Context = rootView.context
        if(context is ReactContext) {
            val params : WritableMap = Arguments.createMap()
            params.putInt("statusCode", statusCode)
            params.putString("message", message)
            context.getJSModule(RCTEventEmitter::class.java).receiveEvent(
                rootView.id, "onAdLoadFailed", params)
        }
    }

    override fun onAdClicked() {
        Log.e(REACT_CLASS_NAME, "onAdClicked()")
        val context : Context = rootView.context
        if(context is ReactContext) {
            context.getJSModule(RCTEventEmitter::class.java).receiveEvent(
                rootView.id, "onAdClicked", null)
        }
    }

    override fun getExportedCustomDirectEventTypeConstants() : Map<String, Any> {
        Log.e(REACT_CLASS_NAME, "getExportedCustomDirectEventTypeConstants()")
        return mapOf<String, Any>(
            "onAdLoaded" to MapBuilder.of("registrationName", "onAdLoaded"),
            "onAdLoadFailed" to MapBuilder.of("registrationName", "onAdLoadFailed"),
            "onAdClicked" to MapBuilder.of("registrationName", "onAdClicked"),
        )
    }
}