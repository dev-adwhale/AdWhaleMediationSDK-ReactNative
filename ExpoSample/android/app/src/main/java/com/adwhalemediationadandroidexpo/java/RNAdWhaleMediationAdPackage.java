package com.adwhalemediationadandroidexpo.java;


import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RNAdWhaleMediationAdPackage implements ReactPackage {

    private RNAdWhaleMediationAdSettingModule rnAdWhaleMediationAdSettingModule;

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactApplicationContext) {
        List<NativeModule> modules = new ArrayList<>();
        rnAdWhaleMediationAdSettingModule = new RNAdWhaleMediationAdSettingModule(reactApplicationContext);
        RNAdWhaleMediationInterstitialAd rnAdWhaleMediationInterstitialAd = new RNAdWhaleMediationInterstitialAd(reactApplicationContext);
        RNAdWhaleMediationRewardAd rnAdWhaleMediationRewardAd = new RNAdWhaleMediationRewardAd(reactApplicationContext);
        modules.add(rnAdWhaleMediationAdSettingModule);
        modules.add(rnAdWhaleMediationInterstitialAd);
        modules.add(rnAdWhaleMediationRewardAd);
        return modules;
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactApplicationContext) {
        return Arrays.<ViewManager>asList(new RNAdWhaleMediationAdView(rnAdWhaleMediationAdSettingModule));
    }
}
