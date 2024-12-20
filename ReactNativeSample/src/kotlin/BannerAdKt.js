import React, { Component } from "react";
import { View, Button, StyleSheet, Dimensions, Text, TouchableOpacity } from "react-native";
import AdWhaleMediationAdView from './AdWhaleMediationAdViewKt';

const windowWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
    container: {
      justifyContent: 'flex-start',
      alignItems: 'center',
      paddingTop: 20,
    },
    buttonContainer: {
      width: '100%',
    },
    buttonWrapper: {
      marginBottom: 5,
      width: '80%',
      alignSelf: 'center',
    },
    adWhaleMediationAdViewStyle: {
      height: 250,
      width: windowWidth,
      backgroundColor: 'yellow',
      marginBottom: 20,
    },
});

const radioStyles = StyleSheet.create({
  radioGroup: {
    justifyContent: 'space-around',
    marginBottom: 10,
  },
  radioButton: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  outerCircle: {
    height: 24,
    width: 24,
    borderRadius: 12,
    borderWidth: 2,
    borderColor: '#000',
    alignItems: 'center',
    justifyContent: 'center',
    marginRight: 10,
  },
  innerCircle: {
    height: 12,
    width: 12,
    borderRadius: 6,
    backgroundColor: '#000',
  },
})

  class BannerAd extends Component {
    state = {
        isReload: false,
        selectedSize: 'BANNER320x50',   // 기본 사이즈 설정
    };

    onPressBannerLoadButton = () => {
        console.log('banner load');
        this.setState(prevState => ({ isReload: !prevState.isReload }));
    }

    // 배너 광고 수신 성공 시 이벤트 콜백 처리
    _onAdLoaded = (event) => {
        console.warn("[BannerAdKt.js]js did registered _onAdLoaded successfully!!!" + event);
        this.setState({ isReload: false });
    }
    
      // 배너 광고 수신 실패 시 이벤트 콜백 처리
    _onAdLoadFailed = (event) => {
      const { statusCode, message } = event.nativeEvent;
        console.warn("[BannerAdKt.js]js did registered _onAdLoadFailed successfully!!! status code: " + statusCode + ", message: " + message);
        this.setState({ isReload: false });
    }
    
      // 배너 광고 클릭하여 랜딩 페이지로 넘어갈 때 이벤트 콜백 처리(html5 소재의 배너 광고일 경우에만 적용)  
    _onAdClicked = (event) => {
        console.warn("[BannerAdKt.js]js did registered _onAdClicked successfully!!!" + event);
    }

    // 라디오 버튼 선택 시 호출
    handleSizeChange = (size) => {
      this.setState({ selectedSize: size});
    }

    render() {
        const { selectedSize } = this.state;

        return (
            <View style={styles.container}>
              <View style={radioStyles.radioGroup}>
                {['BANNER320x50', 'BANNER320x100', 'BANNER300x250', 'BANNER250x250'].map(size => (
                  <TouchableOpacity
                    key={size}
                    style={radioStyles.radioButton}
                    onPress={() => this.handleSizeChange(size)}
                  >
                    <View style={radioStyles.outerCircle}>
                      {selectedSize === size && <View style={radioStyles.innerCircle} /> }
                    </View>
                    <Text>{size}</Text>
                  </TouchableOpacity>
                ))}
              </View>

            <View style={styles.buttonContainer}>
                <View style={styles.buttonWrapper}>
                <Button title="Banner load" onPress={this.onPressBannerLoadButton} />
                </View>
            </View>
            
            <AdWhaleMediationAdView
                style={styles.adWhaleMediationAdViewStyle}
                placementUid="발급받은 placement uid 값"
                adSize={selectedSize}
                loadAd={this.state.isReload}
                onAdLoaded={this._onAdLoaded}
                onAdLoadFailed={this._onAdLoadFailed}
                onAdClicked={this._onAdClicked}
            />
            </View>
        );
    }
  }

  export default BannerAd;