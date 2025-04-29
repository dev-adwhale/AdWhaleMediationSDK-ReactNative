package com.adwhalemediationadandroidexpo.kotlin

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class RNAdWhaleMediationAdPackageKt : ReactPackage {

    private lateinit var rnAdWhaleMediationAdSettingModuleKt : RNAdWhaleMediationAdSettingModuleKt

    override fun createNativeModules(reactApplicationContext : ReactApplicationContext) : List<NativeModule> {
        rnAdWhaleMediationAdSettingModuleKt = RNAdWhaleMediationAdSettingModuleKt(reactApplicationContext)
        return listOf<NativeModule>(rnAdWhaleMediationAdSettingModuleKt, RNAdWhaleMediationInterstitialAdKt(reactApplicationContext), RNAdWhaleMediationRewardAdKt(reactApplicationContext))
    }

    override fun createViewManagers(reactApplicationContext : ReactApplicationContext) : List<ViewManager<*, *>> {
        return listOf<ViewManager<*, *>>(RNAdWhaleMediationAdViewKt(rnAdWhaleMediationAdSettingModuleKt))
    }
}