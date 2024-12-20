import React, { Component } from "react";
import { View, Button, StyleSheet, NativeModules, NativeEventEmitter } from 'react-native';

const { RNAdWhaleMediationInterstitialAdKt } = NativeModules;

const styles = StyleSheet.create({
    buttonWrapper: {
        marginBottom: 10,
        width: '80%',
    },
    disabledButton: {
        opacity: 0.5,
    },
});

class InterstitialAd extends Component {
    state = {
        isInterstitialAdLoaded: false,
    };

    componentDidMount() {
        this._interstitialAdInit();
    }

    _interstitialAdInit = () => {
        RNAdWhaleMediationInterstitialAdKt.initialize("발급받은 placement uid 값");
        const eventEmitter = new NativeEventEmitter(RNAdWhaleMediationInterstitialAdKt);
        eventEmitter.addListener('onInterstitialAdLoaded', this._onInterstitialAdLoaded);
        eventEmitter.addListener('onInterstitialAdShowed', this._onInterstitialAdShowed);
        eventEmitter.addListener('onInterstitialAdClosed', this._onInterstitialAdClosed);
        eventEmitter.addListener('onInterstitialAdLoadFailed', this._onInterstitialAdLoadFailed);
        eventEmitter.addListener('onInterstitialAdShowFailed', this._onInterstitialAdShowFailed);
    }


    onPressInterstitialRequestButton = () => {
        RNAdWhaleMediationInterstitialAdKt.loadAd();
    }

    onPressInterstitialShowButton = () => {
        RNAdWhaleMediationInterstitialAdKt.showAd();
    }

    _onInterstitialAdLoaded = () => {
        console.log("[InterstitialAdKt.js]js did registered _onInterstitialAdLoaded successfully!!!");
        this.setState({ isInterstitialAdLoaded: true });
    }

    _onInterstitialAdLoadFailed = (event) => {
        const { statusCode, message } = event;
        console.log("[InterstitialAdKt.js]js did registered _onInterstitialAdLoadFailed successfully!!! status code: " + statusCode + ", message: " + message);
        this.setState({ isInterstitialAdLoaded: false });
    }

    _onInterstitialAdShowed = () => {
        console.log("[InterstitialAdKt.js]js did registered _onInterstitialAdShowed successfully!!!");
    }

    _onInterstitialAdShowFailed = (event) => {
        const { statusCode, message } = event;
        console.log("[InterstitialAdKt.js]js did registered _onInterstitialAdShowFailed successfully!!! status code: " + statusCode + ", message: " + message);
        this.setState({ isInterstitialAdLoaded: false });
    }

    _onInterstitialAdClosed = () => {
        console.log("[InterstitialAdKt.js]js did registered _onInterstitialAdClosed successfully!!!");
        this.setState({ isInterstitialAdLoaded: false });
    }

    render() {
        return (
            <View>
                <View style={styles.buttonWrapper}>
                    <Button title="Interstitial Ad Load" onPress={this.onPressInterstitialRequestButton} /> 
                </View>
                <View style={styles.buttonWrapper}>
                    <Button 
                        title="Interstitial Ad Show"
                        onPress={this.onPressInterstitialShowButton}
                        disabled={!this.state.isInterstitialAdLoaded}
                        style={!this.state.isInterstitialAdLoaded ? styles.disabledButton : null}
                    />
                </View>
            </View>
        );
    }
}

export default InterstitialAd;
