import { StatusBar } from 'expo-status-bar';
import React, {Component} from 'react';
import {SafeAreaView, View, ScrollView, StyleSheet, NativeModules, NativeEventEmitter } from 'react-native';
import BannerAd from './src/kotlin/BannerAdKt';
import InterstitialAd from './src/kotlin/InterstitialAdKt';
import RewardAd from './src/kotlin/RewardAdKt';


// 상수 정의
const { RNAdWhaleMediationAdSettingModuleKt } = NativeModules;

// 스타일 정의
const styles = StyleSheet.create({
    safeArea: {
        flex: 1,
        backgroundColor: 'white',
    },
    mainViewStyle: {
        flex: 1,
        marginTop: 50
    },
    contentViewStyle: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center'                
    },
});

class App extends Component {
  
    componentDidMount() {
        this._initialize();
    }

    // 화면 렌더링
    render() {
        
    return (            
        <SafeAreaView style={styles.safeArea}>
            <View style={styles.mainViewStyle}>
                <ScrollView>
                    <View style={styles.contentViewStyle}>
                        <BannerAd />
                        <InterstitialAd />
                        <RewardAd />
                    </View>
                </ScrollView>
            </View>
        </SafeAreaView>
    );
}

    // SDK 초기화
    _initialize = () => {
        RNAdWhaleMediationAdSettingModuleKt.initialize()
            .then((statusCode) => {
                if (statusCode === 100) {
                    console.log('AdWhale SDK initialized successfully');
                } else {
                    console.error('AdWhale SDK initialization failed');
                }
            })
            .catch((error) => {
                console.error('Error initializing AdWhale SDK:', error);
            });
    }
};

export default App;

