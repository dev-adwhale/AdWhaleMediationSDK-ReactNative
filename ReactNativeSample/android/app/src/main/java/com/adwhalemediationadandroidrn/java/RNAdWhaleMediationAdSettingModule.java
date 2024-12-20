package com.adwhalemediationadandroidrn.java;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import net.adwhale.sdk.mediation.ads.AdWhaleMediationAds;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationOnInitCompleteListener;

import java.util.HashMap;
import java.util.Map;

public class RNAdWhaleMediationAdSettingModule extends ReactContextBaseJavaModule {
    private static final String REACT_CLASS_NAME = RNAdWhaleMediationAdSettingModule.class.getSimpleName();

    public RNAdWhaleMediationAdSettingModule(ReactApplicationContext context) {
        super(context);
    }


    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS_NAME;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void initialize(final Promise promise) {
        Log.i(REACT_CLASS_NAME, ".initialize()");
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.reject("NO_ACTIVITY", "Unable to initialize AdWhale SDK: No activity available");
            return;
        }

        AdWhaleMediationAds.init(currentActivity, new AdWhaleMediationOnInitCompleteListener() {
            @Override
            public void onInitComplete(int statusCode, String message) {
                Log.i(REACT_CLASS_NAME, ".onInitComplete(" + statusCode + ", " + message + ")");
                promise.resolve(statusCode);

//                if(adWhaleMediationAdView != null) {
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//                        @Override
//                        public void run() {
//                            // 배너 뷰 로드
//                            adWhaleMediationAdView.loadAd();
//                        }
//                    });
//                }
            }
        });
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }
}