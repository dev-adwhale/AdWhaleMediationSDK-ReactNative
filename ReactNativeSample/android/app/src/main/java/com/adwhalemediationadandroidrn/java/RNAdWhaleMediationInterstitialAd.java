package com.adwhalemediationadandroidrn.java;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import net.adwhale.sdk.mediation.ads.AdWhaleMediationInterstitialAd;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationInterstitialAdListener;

import java.util.HashMap;
import java.util.Map;

public class RNAdWhaleMediationInterstitialAd extends ReactContextBaseJavaModule implements AdWhaleMediationInterstitialAdListener {

    private static final String REACT_CLASS_NAME = RNAdWhaleMediationInterstitialAd.class.getSimpleName();
    private AdWhaleMediationInterstitialAd adWhaleMediationInterstitialAd;

    public RNAdWhaleMediationInterstitialAd(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS_NAME;
    }

    @ReactMethod
    public void initialize(String placementUid, final Promise promise) {
        Log.e(REACT_CLASS_NAME, "initialize() placementUid: " + placementUid);
        adWhaleMediationInterstitialAd = new AdWhaleMediationInterstitialAd(placementUid);
        adWhaleMediationInterstitialAd.setAdWhaleMediationInterstitialAdListener(this);

//        adWhaleMediationInterstitialAd.setAdWhaleMediationInterstitialAdListener(new AdWhaleMediationInterstitialAdListener() {
//            @Override
//            public void onAdLoaded() {
//                Log.i(REACT_CLASS_NAME, "onAdLoaded()");
//                promise.resolve("onAdLoaded");
//            }
//
//            @Override
//            public void onAdLoadFailed(int statusCode, String message) {
//                Log.i(REACT_CLASS_NAME, "onAdLoadFailed(" + statusCode + ", " + message + ")");
//                promise.reject("onAdLoadFailed", message);
//            }
//
//            @Override
//            public void onAdShowed() {
//                Log.i(REACT_CLASS_NAME, "onAdShowed()");
//                promise.resolve("onAdShowed");
//            }
//
//            @Override
//            public void onAdShowFailed(int statusCode, String message) {
//                Log.i(REACT_CLASS_NAME, "onAdShowFailed(" + statusCode + ", " + message + ")");
//                promise.reject("onAdShowFailed", message);
//            }
//
//            @Override
//            public void onAdClosed() {
//                Log.i(REACT_CLASS_NAME, "onAdClosed()");
//                promise.resolve("onAdClosed");
//            }
//        });
    }

    @ReactMethod
    public void loadAd() {
        Log.e(REACT_CLASS_NAME, ".loadAd()");

        if (adWhaleMediationInterstitialAd == null) {
            Log.e(REACT_CLASS_NAME, "adWhaleMediationInterstitialAd is null");
            return;
        }

        UiThreadUtil.runOnUiThread(() -> {
            adWhaleMediationInterstitialAd.loadAd();
        });
    }

    @ReactMethod
    public void showAd() {
        Log.e(REACT_CLASS_NAME, "showAd()");
        if (adWhaleMediationInterstitialAd == null) {
            Log.e(REACT_CLASS_NAME, "adWhaleMediationInterstitialAd is null");
            return;
        }

        UiThreadUtil.runOnUiThread(() -> {
            adWhaleMediationInterstitialAd.showAd();
        });
    }

    @Override
    public void onAdLoaded() {
        Log.i(REACT_CLASS_NAME, "onAdLoaded()");
        sendEvent("onInterstitialAdLoaded", null);

    }

    @Override
    public void onAdLoadFailed(int statusCode, String message) {
        Log.i(REACT_CLASS_NAME, "onAdLoadFailed(" + statusCode + ", " + message + ")");
        WritableMap params = Arguments.createMap();
        params.putInt("statusCode", statusCode);
        params.putString("message", message);
        sendEvent("onInterstitialAdLoadFailed", params);
    }

    @Override
    public void onAdShowed() {
        Log.i(REACT_CLASS_NAME, "onAdShowed()");
        sendEvent("onInterstitialAdShowed", null);
    }

    @Override
    public void onAdShowFailed(int statusCode, String message) {
        Log.i(REACT_CLASS_NAME, "onAdShowFailed(" + statusCode + ", " + message + ")");
        WritableMap params = Arguments.createMap();
        params.putInt("statusCode", statusCode);
        params.putString("message", message);
        sendEvent("onInterstitialAdShowFailed", params);
    }

    @Override
    public void onAdClosed() {
        Log.i(REACT_CLASS_NAME, "onAdClosed()");
        sendEvent("onInterstitialAdClosed", null);
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @ReactMethod
    public void addListener(String eventName) {
        //TODO NOTHING
    }

     @ReactMethod
     public void removeListeners(Integer count) {
        //TODO NOTHING
     }
}
