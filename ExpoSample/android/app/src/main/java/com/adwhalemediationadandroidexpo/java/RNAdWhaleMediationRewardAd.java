package com.adwhalemediationadandroidexpo.java;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import net.adwhale.sdk.mediation.ads.AdWhaleMediationFullScreenContentCallback;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardAd;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardItem;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardedAdLoadCallback;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationUserEarnedRewardListener;

import java.util.HashMap;
import java.util.Map;

public class RNAdWhaleMediationRewardAd extends ReactContextBaseJavaModule implements AdWhaleMediationRewardedAdLoadCallback, AdWhaleMediationFullScreenContentCallback, AdWhaleMediationUserEarnedRewardListener {

    private static final String REACT_CLASS_NAME = RNAdWhaleMediationRewardAd.class.getSimpleName();
    private AdWhaleMediationRewardAd adWhaleMediationRewardAd;

    public RNAdWhaleMediationRewardAd(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS_NAME;
    }

    @ReactMethod
    public void initialize(String placementUid) {
        Log.e(REACT_CLASS_NAME, "initialize() placementUid: " + placementUid);
        adWhaleMediationRewardAd = new AdWhaleMediationRewardAd(placementUid);
        adWhaleMediationRewardAd.setAdWhaleMediationFullScreenContentCallback(this);
    }

    @ReactMethod
    public void loadAd() {
        Log.e(REACT_CLASS_NAME, "loadAd()");
        if (adWhaleMediationRewardAd == null) {
            Log.e(REACT_CLASS_NAME, "adWhaleMediationRewardAd is null");
            return;
        }

        UiThreadUtil.runOnUiThread(() -> {
            adWhaleMediationRewardAd.loadAd(this);
        });
    }

    @ReactMethod
    public void showAd() {
        Log.e(REACT_CLASS_NAME, "showAd()");
        if (adWhaleMediationRewardAd == null) {
            Log.e(REACT_CLASS_NAME, "adWhaleMediationRewardAd is null");
            return;
        }

        UiThreadUtil.runOnUiThread(() -> {
            adWhaleMediationRewardAd.showAd(this);
        });
    }


    /// AdWhaleMediationFullScreenContentCallback
    @Override
    public void onAdClicked() {
        Log.i(REACT_CLASS_NAME, "onAdClicked()");
        sendEvnet("onRewardAdClicked", null);
    }

    @Override
    public void onAdDismissed() {
        Log.i(REACT_CLASS_NAME, "onAdDismissed()");
        sendEvnet("onRewardAdDismissed", null);
    }

    @Override
    public void onFailedToShow(int statusCode, String message) {
        Log.i(REACT_CLASS_NAME, "onFailedToShow(" + statusCode + ", " + message + ")");
        WritableMap params = Arguments.createMap();
        params.putInt("statusCode", statusCode);
        params.putString("message", message);
        sendEvnet("onRewardAdFailedToShow", params);
    }

    @Override
    public void onAdShowed() {
        Log.i(REACT_CLASS_NAME, "onAdShowed()");
        sendEvnet("onRewardAdShowed", null);
    }

    /// AdWhaleMediationRewardedAdLoadCallback
    @Override
    public void onAdLoaded(AdWhaleMediationRewardAd adWhaleMediationRewardAd, String message) {
        Log.i(REACT_CLASS_NAME, "onAdLoaded(+ " + message + ")");
        sendEvnet("onRewardAdLoaded", null);
    }

    @Override
    public void onAdFailedToLoad(int statusCode, String message) {
        Log.i(REACT_CLASS_NAME, "onAdFailedToLoad(" + statusCode + ", " + message + ")");
        WritableMap params = Arguments.createMap();
        params.putInt("statusCode", statusCode);
        params.putString("message", message);
        sendEvnet("onRewardAdFailedToLoad", params);
    }


    /// AdWhaleMediationUserEarnedRewardListener
    @Override
    public void onUserRewarded(AdWhaleMediationRewardItem adWhaleMediationRewardItem) {
        Log.i(REACT_CLASS_NAME, "onUserRewarded(" + adWhaleMediationRewardItem.toString() + ")");
        WritableMap params = Arguments.createMap();
        params.putString("type", adWhaleMediationRewardItem.getRewardType());
        params.putInt("amount", adWhaleMediationRewardItem.getRewardAmount());
        sendEvnet("onUserRewarded", params);
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    private void sendEvnet(String eventName, @Nullable WritableMap params) {
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