package com.adwhalemediationadandroidrn.java;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import net.adwhale.sdk.mediation.ads.ADWHALE_AD_SIZE;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationAdView;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationAdViewListener;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationAds;
import net.adwhale.sdk.mediation.ads.AdWhaleMediationOnInitCompleteListener;

import java.util.Map;

public class RNAdWhaleMediationAdView extends ViewGroupManager<RNWrapperView> implements AdWhaleMediationAdViewListener {

    private static final String REACT_CLASS_NAME = RNAdWhaleMediationAdView.class.getSimpleName();
    private RNAdWhaleMediationAdSettingModule rnAdWhaleMediationAdSettingModule;
    private RNWrapperView rootView;

    private AdWhaleMediationAdView adWhaleMediationAdView;
    public RNAdWhaleMediationAdView(RNAdWhaleMediationAdSettingModule rnAdWhaleMediationAdSettingModule){
        this.rnAdWhaleMediationAdSettingModule = rnAdWhaleMediationAdSettingModule;
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS_NAME;
    }

    @NonNull
    @Override
    protected RNWrapperView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        rootView = new RNWrapperView(themedReactContext);
        Log.e(REACT_CLASS_NAME, "rootView created.");
        Log.i(REACT_CLASS_NAME, "getCurrentActivity(): " + themedReactContext.getCurrentActivity());

//        AdWhaleMediationAds.init(themedReactContext.getCurrentActivity(), new AdWhaleMediationOnInitCompleteListener() {
//            @Override
//            public void onInitComplete(int statusCode, String message) {
//                Log.i(REACT_CLASS_NAME, ".onInitComplete(" + statusCode + ", " + message + ")");
//                if(adWhaleMediationAdView != null) {
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//                        @Override
//                        public void run() {
//                            // 배너 뷰 로드
//                            adWhaleMediationAdView.loadAd();
//                        }
//                    });
//                }
//            }
//        });

        adWhaleMediationAdView = new AdWhaleMediationAdView(themedReactContext.getCurrentActivity());
        adWhaleMediationAdView.setAdWhaleMediationAdViewListener(this);
//        adWhaleMediationAdView.setPlacementUid("AU1731554011712");
//        adWhaleMediationAdView.setAdwhaleAdSize(ADWHALE_AD_SIZE.BANNER320x50);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rootView.addView(adWhaleMediationAdView, params);
        Log.e(REACT_CLASS_NAME, "adWhaleMediationAdView is attached to rootView.");

        return rootView;
    }

    @ReactProp(name = "placementUid")
    public void setPlacementUid(RNWrapperView view, String placementUid) {
        adWhaleMediationAdView.setPlacementUid(placementUid);
    }

    @ReactProp(name = "adSize")
    public void setAdSize(RNWrapperView view, String adSize) {
        adWhaleMediationAdView.setAdwhaleAdSize(ADWHALE_AD_SIZE.valueOf(adSize));
    }

    @ReactProp(name = "loadAd")
    public void loadAd(RNWrapperView view, boolean isReload) {
        Log.e(REACT_CLASS_NAME, "loadAd()");
        if (adWhaleMediationAdView != null && isReload) {
            adWhaleMediationAdView.loadAd();
        }
    }

    @Override
    public void onAdLoaded() {
        Log.e(REACT_CLASS_NAME, "onAdLoaded()");
        final Context context = rootView.getContext();
        if (context instanceof ReactContext) {
            ((ReactContext) context).getJSModule(RCTEventEmitter.class)
                    .receiveEvent(rootView.getId(),
                            "onAdLoaded", null);
        }
    }

    @Override
    public void onAdLoadFailed(int statusCode, String message) {
        Log.e(REACT_CLASS_NAME, "onAdLoadFailed(" + statusCode + "," + message + ")");
        final Context context = rootView.getContext();
        if (context instanceof ReactContext) {
            WritableMap params = Arguments.createMap();
            params.putInt("statusCode", statusCode);
            params.putString("message", message);
            ((ReactContext) context).getJSModule(RCTEventEmitter.class)
                    .receiveEvent(rootView.getId(),
                            "onAdLoadFailed", params);
        }
    }

    @Override
    public void onAdClicked() {
        Log.e(REACT_CLASS_NAME, "onAdClicked()");
        final Context context = rootView.getContext();
        if (context instanceof ReactContext) {
            ((ReactContext) context).getJSModule(RCTEventEmitter.class)
                    .receiveEvent(rootView.getId(),
                            "onAdClicked", null);
        }
    }


    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Log.e(REACT_CLASS_NAME, "getExportedCustomDirectEventTypeConstants()");
        return MapBuilder.<String, Object>builder()
                .put("onAdLoaded",
                        MapBuilder.of("registrationName", "onAdLoaded"))
                .put("onAdLoadFailed",
                        MapBuilder.of("registrationName", "onAdLoadFailed"))
                .put("onAdClicked",
                        MapBuilder.of("registrationName", "onAdClicked"))
                .build();


    }
}

