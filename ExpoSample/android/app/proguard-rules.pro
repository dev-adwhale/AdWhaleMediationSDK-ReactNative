# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /usr/local/Cellar/android-sdk/24.3.3/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# react-native-reanimated
-keep class com.swmansion.reanimated.** { *; }
-keep class com.facebook.react.turbomodule.** { *; }

# Add any project specific keep options here:
#================== AdWhale Mediation SDK Proguard for Release 적용 코드 시작 ==================
-keepclasseswithmembers class net.adwhale.sdk.mediation.ads.AdWhaleMediationAds {
    public static *** init(***);
    public static *** init(***, ***);
}

-keep interface net.adwhale.sdk.mediation.ads.AdWhaleMediationOnInitCompleteListener {*;}

-keepclasseswithmembers class net.adwhale.sdk.utils.AdWhaleLog {
    public static *** setLogLevel(***);
    public static *** getLogLevel();
}

-keep class net.adwhale.sdk.mediation.ads.ADWHALE_AD_SIZE {*;}
-keep class net.adwhale.sdk.mediation.ads.ADWHALE_RESULT_CODE {*;}
-keep class net.adwhale.sdk.utils.AdWhaleLog$LogLevel{*;}
-keep class net.adwhale.sdk.impl.mediation.ReqMediationAdConfig {*;}
-keep class net.adwhale.sdk.impl.mediation.ResMediationAdConfig {*;}
-keep class net.adwhale.sdk.impl.mediation.ResMediation {*;}

-keepclasseswithmembers class net.adwhale.sdk.mediation.ads.AdWhaleMediationInterstitialAd {
    public <init>(...);
    public *** loadAd();
    public *** showAd();
    public *** showAd(***);
    public *** destroy();
    public *** setAdWhaleMediationInterstitialAdListener(***);
}

-keep interface net.adwhale.sdk.mediation.ads.AdWhaleMediationInterstitialAdListener {*;}

-keepclasseswithmembers class net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardAd {
    public <init>(...);
    public *** loadAd(***);
    public *** showAd(***);
    public *** destroy();
    public *** setAdWhaleMediationFullScreenContentCallback(***);
}

-keep class net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardItem {*;}
-keep interface net.adwhale.sdk.mediation.ads.AdWhaleMediationFullScreenContentCallback {*;}
-keep interface net.adwhale.sdk.mediation.ads.AdWhaleMediationRewardedAdLoadCallback {*;}
-keep interface net.adwhale.sdk.mediation.ads.AdWhaleMediationUserEarnedRewardListener {*;}

-keepclasseswithmembers class net.adwhale.sdk.mediation.ads.AdWhaleMediationNativeAdView {
    public <init>(...);
    public *** loadAd();
    public *** destroy();
    public *** setAdWhaleMediationNativeAdViewListener(***);
    public *** setNativeAdSize(***, ***);
    public *** setPlacementUid(***);
    public *** resume();
    public *** pause();
}

-keep interface net.adwhale.sdk.mediation.ads.AdWhaleMediationNativeAdViewListener {*;}

-keepclasseswithmembers class net.adwhale.sdk.mediation.ads.AdWhaleMediationAdView {
    public <init>(...);
    public *** loadAd();
    public *** destroy();
    public *** setAdWhaleMediationAdViewListener(***);
    public *** setAdwhaleAdSize(***);
    public *** setPlacementUid(***);
    public *** resume();
    public *** pause();
}

-keep interface net.adwhale.sdk.mediation.ads.AdWhaleMediationAdViewListener {*;}

-keepclasseswithmembers class net.adwhale.sdk.mediation.ads.AdWhaleMediationAdBannerView {
    public <init>(...);
    public *** loadAd();
    public *** show();
    public *** destroy();
    public *** setAdWhaleMediationAdBannerViewListener(***);
    public *** resume();
    public *** pause();
}

-keep interface net.adwhale.sdk.mediation.ads.AdWhaleMediationAdBannerViewListener {*;}
#================== AdWhale Mediation SDK Proguard for Release 적용 코드 끝 ==================

#================== AdWhale Cauly Adapter SDK Proguard for Release 적용 코드 시작 ==================

-keep class net.adwhale.sdk.cauly.adapter.CaulyAdBannerLoader {*;}

-keep class net.adwhale.sdk.cauly.adapter.CaulyAdBannerPreLoader {*;}

-keep class net.adwhale.sdk.cauly.adapter.CaulyAdInterstitialLoader {*;}

#================== AdWhale Cauly Adapter SDK Proguard for Release 적용 코드 끝 ==================

#================== AdWhale Admize Adapter SDK Proguard for Release 적용 코드 시작 ==================

-keep class net.adwhale.sdk.admize.adapter.AdmizeAdBannerLoader {*;}

-keep class net.adwhale.sdk.admize.adapter.AdmizeAdBannerPreLoader {*;}

-keep class net.adwhale.sdk.admize.adapter.AdmizeAdInterstitialLoader {*;}

-keep class net.adwhale.sdk.admize.adapter.AdmizeAdRewardLoader {*;}

#================== AdWhale Admize Adapter SDK Proguard for Release 적용 코드 끝 ==================

#================== AdWhale AdFit Adapter SDK Proguard for Release 적용 코드 시작 ==================

-keep class net.adwhale.sdk.adfit.adapter.AdFitAdBannerLoader {*;}

-keep class net.adwhale.sdk.adfit.adapter.AdFitAdBannerPreLoader {*;}

#================== AdWhale AdFit Adapter SDK Proguard for Release 적용 코드 끝 ==================

#================== AdWhale AdManager Adapter SDK Proguard for Release 적용 코드 시작 ==================

-keep class net.adwhale.sdk.admob.adapter.AdManagerAdBannerLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdManagerAdBannerPreLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdManagerAdInterstitialLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdManagerAdNativeLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdManagerAdRewardedInterstitialLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdManagerAdRewardLoader {*;}

#================== AdWhale AdManager Adapter SDK Proguard for Release 적용 코드 끝 ==================

#================== AdWhale Admob Adapter SDK Proguard for Release 적용 코드 시작 ==================

-keep class net.adwhale.sdk.admob.adapter.AdmobAdBannerLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdmobAdBannerPreLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdmobAdInterstitialLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdmobAdNativeLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdmobAdRewardedInterstitialLoader {*;}

-keep class net.adwhale.sdk.admob.adapter.AdmobAdRewardLoader {*;}

#================== AdWhale Admob Adapter SDK Proguard for Release 적용 코드 끝 ==================

#================== AdWhale LevelPlay Adapter SDK Proguard for Release 적용 코드 시작 ==================

-keep class net.adwhale.sdk.levelplay.adapter.LevelPlayAdBannerLoader {*;}

-keep class net.adwhale.sdk.levelplay.adapter.LevelPlayAdBannerPreLoader {*;}

-keep class net.adwhale.sdk.levelplay.adapter.LevelPlayAdInterstitialLoader {*;}

-keep class net.adwhale.sdk.levelplay.adapter.LevelPlayAdRewardLoader {*;}

#================== AdWhale LevelPlay Adapter SDK Proguard for Release 적용 코드 끝 ==================
