import React, { Component } from 'react';
import { View, Button, StyleSheet, NativeModules, NativeEventEmitter } from 'react-native';

const { RNAdWhaleMediationRewardAd } = NativeModules;

const styles = StyleSheet.create({
  buttonWrapper: {
      marginBottom: 10,
      width: '80%',
  },
  disabledButton: {
      opacity: 0.5,
  },
});

class RewardAd extends Component {
  state = {
    isRewardAdLoaded: false,
  };

  componentDidMount() {
    this._rewardAdInit();
  }

  _rewardAdInit = () => {
    const eventEmitter = new NativeEventEmitter(RNAdWhaleMediationRewardAd);
    eventEmitter.addListener('onRewardAdLoaded', this._onRewardAdLoaded);
    eventEmitter.addListener('onRewardAdShowed', this._onRewardAdShowed);
    eventEmitter.addListener('onRewardAdDismissed', this._onRewardAdDismissed);
    eventEmitter.addListener('onRewardAdClicked', this._onRewardAdClicked);
    eventEmitter.addListener('onRewardAdFailedToLoad', this._onRewardAdFailedToLoad);
    eventEmitter.addListener('onRewardAdFailedToShow', this._onRewardAdFailedToShow);
    eventEmitter.addListener('onUserRewarded', this._onRewardAdUserRewarded);
  }

  onPressRewardRequestButton = () => {
    RNAdWhaleMediationRewardAd.loadAd("발급받은 placement uid 값");
  }

  onPressRewardShowButton = () => {
    RNAdWhaleMediationRewardAd.showAd();
  }

  _onRewardAdLoaded = () => {
    console.log("[RewardAd.js]js did registered _onRewardAdLoaded successfully!!!");
    this.setState({ isRewardAdLoaded: true });
  }

  _onRewardAdFailedToLoad = (event) => {
    const { statusCode, message } = event;
    console.log("[RewardAd.js]js did registered _onRewardAdFailedToLoad successfully!!! status code: " + statusCode + ", message: " + message);
    this.setState({ isRewardAdLoaded: false });
  }

  _onRewardAdShowed = () => {
    console.log("[RewardAd.js]js did registered _onRewardAdShowed successfully!!!");
  }

  _onRewardAdFailedToShow = (event) => {
    const { statusCode, message } = event;
    console.log("[RewardAd.js]js did registered _onRewardAdFailedToShow successfully!!! status code: " + statusCode + ", message: " + message);
    this.setState({ isRewardAdLoaded: false });
  }

  _onRewardAdDismissed = () => {
    console.log("[RewardAd.js]js did registered _onRewardAdDismissed successfully!!!");
    this.setState({ isRewardAdLoaded: false });
  }

  _onRewardAdClicked = () => {
    console.log("[RewardAd.js]js did registered _onRewardAdClicked successfully!!!");
  }

  _onRewardAdUserRewarded = (event) => {
    const { type, amount } = event;
    console.log("[RewardAd.js]js did registered _onRewardAdUserRewarded successfully!!!");
  }

  render() {
    return (
      <View>
        <View style={styles.buttonWrapper}>
          <Button title="Reward Ad Load" onPress={this.onPressRewardRequestButton} />
        </View>
        <View style={styles.buttonWrapper}>
        <Button 
          title="Reward Ad Show" 
          onPress={this.onPressRewardShowButton}
          disabled={!this.state.isRewardAdLoaded}
          style={!this.state.isRewardAdLoaded ? styles.disabledButton : null}
        />
        </View>
      </View>
    );
  }
}

export default RewardAd;